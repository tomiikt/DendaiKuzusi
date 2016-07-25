package jp.ac.dendai.c.jtp.dendaikuzusi;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
public class Yuka extends AbstractDrawable {
    //インデックスバッファの生成
    //3つの頂点で三角形を作成
    private static final byte[] indexs = new byte[]{
            0,1,2,
            2,1,3,
            2,3,4,
            4,3,5,
            1,6,3,
            3,6,7,
            3,7,5,
            5,7,8,
    };
    //コンストラクタ
    public Yuka() {
        super(new float[][]{Constant.green, Constant.brown});
        //頂点バッファの生成
        vertexs = new float[] {
                Constant.YukaSIZE, Constant.YukaY, Constant.YukaSIZE,	//頂点0
                Constant.YukaZERO, Constant.YukaY, Constant.YukaSIZE,	//頂点1
                Constant.YukaSIZE, Constant.YukaY, Constant.YukaZERO,	//頂点2
                Constant.YukaZERO, Constant.YukaY, Constant.YukaZERO,	//頂点3
                Constant.YukaSIZE, Constant.YukaY, -Constant.YukaSIZE,	//頂点4
                Constant.YukaZERO, Constant.YukaY, -Constant.YukaSIZE,	//頂点5
                -Constant.YukaSIZE, Constant.YukaY, Constant.YukaSIZE,	//頂点6
                -Constant.YukaSIZE, Constant.YukaY, Constant.YukaZERO,	//頂点7
                -Constant.YukaSIZE, Constant.YukaY, -Constant.YukaSIZE,	//頂点8
        };
    }
    //描画
    @Override
    public void draw(GL10 gl10) {
        //頂点配列の有効化
        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        //バッファ変換
        FloatBuffer vertexBuffer = makeFloatBuffer(vertexs);
        ByteBuffer indexBuffer = makeByteBuffer(indexs);

        //頂点バッファの指定
        gl10.glVertexPointer(3,GL10.GL_FLOAT,0,vertexBuffer);

        //面の描画
        setColor(gl10, materialColors[0]);
        indexBuffer.position(0);
        gl10.glDrawElements(GL10.GL_TRIANGLES,
                6,GL10.GL_UNSIGNED_BYTE,indexBuffer);

        //面の描画
        setColor(gl10, materialColors[1]);
        indexBuffer.position(6);
        gl10.glDrawElements(GL10.GL_TRIANGLES,
                6,GL10.GL_UNSIGNED_BYTE,indexBuffer);

        //面の描画
        setColor(gl10, materialColors[1]);
        indexBuffer.position(12);
        gl10.glDrawElements(GL10.GL_TRIANGLES,
                6,GL10.GL_UNSIGNED_BYTE,indexBuffer);

        //面の描画
        setColor(gl10, materialColors[0]);
        indexBuffer.position(18);
        gl10.glDrawElements(GL10.GL_TRIANGLES,
                6,GL10.GL_UNSIGNED_BYTE,indexBuffer);
    }
}