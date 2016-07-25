package jp.ac.dendai.c.jtp.dendaikuzusi;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public abstract class AbstractDrawable implements Drawable{
    protected final float[][] materialColors;
    protected float[] vertexs;

    public AbstractDrawable(float[][] floats) {
        materialColors = floats;
    }

    //float配列をFloatBufferに変換
    protected FloatBuffer makeFloatBuffer(float[] array) {
        FloatBuffer fb= ByteBuffer.allocateDirect(array.length * 4).order(
                ByteOrder.nativeOrder()).asFloatBuffer();
        fb.put(array).position(0);
        return fb;
    }

    //byte配列をByteBufferに変換
    protected ByteBuffer makeByteBuffer(byte[] array) {
        ByteBuffer bb=ByteBuffer.allocateDirect(array.length).order(
                ByteOrder.nativeOrder());
        bb.put(array).position(0);
        return bb;
    }

    protected void setColor(GL10 gl10, float[] color){
        gl10.glMaterialfv(GL10.GL_FRONT_AND_BACK,GL10.GL_AMBIENT, color, 0);
        gl10.glMaterialfv(GL10.GL_FRONT_AND_BACK,GL10.GL_DIFFUSE, color, 0);
    }
}
