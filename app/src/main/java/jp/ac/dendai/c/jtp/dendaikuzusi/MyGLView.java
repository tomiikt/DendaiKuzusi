package jp.ac.dendai.c.jtp.dendaikuzusi;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGLView extends GLSurfaceView  implements Runnable {
    private MyRenderer renderer;
    private Thread        thread;
    private final Object lock;
    public MyGLView(Context context, ScoreView scoreView) {
        super(context);
        lock = new Object();
        this.renderer = new MyRenderer(lock,scoreView);
        setRenderer(renderer);
        setOnTouchListener(renderer.getOnTouchListener());

    }
    @Override
    public void run() {
        double previous = (double) System.currentTimeMillis();
        double now;
        while (thread != null) {
            synchronized (lock) {
                now = System.currentTimeMillis();
                float tstep = (float) ((now - previous) / Constant.tic);

                renderer.tick(tstep);
            }
            previous = now;

            try {
                Thread.sleep(Constant.tic);
            } catch (Exception ignored) {
            }
        }
    }

    public void threadpause(){
        thread = null;
    }

    public void threadstart(){
        thread = new Thread(this);
        thread.start();
    }
}