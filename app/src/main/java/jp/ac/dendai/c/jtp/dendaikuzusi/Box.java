package jp.ac.dendai.c.jtp.dendaikuzusi;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
public class Box extends AbstractDrawable implements Touchable {
    //インデックスバッファの生成
    private static final byte[] indexs = new byte[]{
            0,1,2,
            2,1,3,
            2,3,6,
            6,3,7,
            6,7,4,
            4,7,5,
            4,5,0,
            0,5,1,
            1,5,3,
            3,5,7,
            0,2,4,
            4,2,6,
    };
    private final Vect center;
    private final float size;
    private boolean alive;
    public Box(float[] materialcolor, Vect v, float _size) {
        super(new float[][]{materialcolor});
        alive = true;
        size=_size;
        float mx = v.getX() - _size;
        float my = v.getY() - _size;
        float mz = v.getZ() - _size;

        //頂点バッファの生成
        vertexs=new float[]{
                v.getX(),  v.getY(),  v.getZ(),	//頂点0
                v.getX(),  v.getY(), mz,	//頂点1
                mx,  v.getY(),  v.getZ(),	//頂点2
                mx,  v.getY(), mz,	//頂点3
                v.getX(), my,  v.getZ(),	//頂点4
                v.getX(), my, mz,	//頂点5
                mx, my,  v.getZ(),	//頂点6
                mx, my, mz,	//頂点7
        };
        center = new Vect(v.getX()-_size/2,v.getY()-_size/2,v.getZ()-_size/2);
    }
    public static Box createBox(){
        float[] color = Constant.colorList[((int) (Math.random() * Constant.colorList.length))];
        float rx= randomPosition();
        float ry= randomPosition();
        return new Box(color, new Vect(rx,1.0f,ry),Constant.boxsize);
    }

    private static float randomPosition() {
        return (float) ((Math.random()*2* Constant.YukaSIZE-Constant.YukaSIZE)*Constant.AreaRatio);
    }

    @Override
    public void die(){
        alive=false;
    }
    @Override
    public boolean isAlive(){
        return alive;
    }

    //ボックスの描画
    @Override
    public void draw(GL10 gl10) {
        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        setColor(gl10,materialColors[0]);

        //バッファ変換
        FloatBuffer vertexBuffer = makeFloatBuffer(vertexs);
        ByteBuffer indexBuffer = makeByteBuffer(indexs);

        //頂点バッファの指定
        gl10.glVertexPointer(3,GL10.GL_FLOAT,0,vertexBuffer);

        //面の描画
        gl10.glDrawElements(GL10.GL_TRIANGLES,
                indexBuffer.capacity(),GL10.GL_UNSIGNED_BYTE,indexBuffer);
    }
    @Override
    public boolean isContained(Vect v){
        return center.squareOfDistance(v)<size*size;
    }
}