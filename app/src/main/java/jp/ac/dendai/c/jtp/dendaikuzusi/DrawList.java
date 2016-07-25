package jp.ac.dendai.c.jtp.dendaikuzusi;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;
public class DrawList<E extends Drawable > extends ArrayList<E> {
    public void draw(GL10 gl10){
        for(E d: this){
            d.draw(gl10);
        }
    }
}
