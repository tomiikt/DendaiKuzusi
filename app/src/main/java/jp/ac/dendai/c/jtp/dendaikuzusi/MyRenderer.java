package jp.ac.dendai.c.jtp.dendaikuzusi;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.view.MotionEvent;
import android.view.View;

import java.util.Iterator;


public class MyRenderer implements Renderer {
    private final Object lock;
    private final ScoreView score;
    private Angle screen;
    private volatile Position touch;
    private Scene scene = Scene.S_START;
    private Anata anata;
    private DrawList<Drawable> list;

    public MyRenderer(Object lock, ScoreView scoreView){
        this.lock = lock;
        score = scoreView;
        synchronized (lock) {
            list = new DrawList<Drawable>();
            list.add(new Yuka());
            list.add(new Box(Constant.red, new Vect(1.0f, 1.0f, 1.0f), 2.0f));
            list.add(new Box(Constant.blue, new Vect(4.0f, 1.0f, 4.0f), 2.0f));
            list.add(new Box(Constant.yellow, new Vect(8.0f, 1.0f, 8.0f), 2.0f));
        }
        touch = new Position();
        anata = new Anata();
    }

    @Override
    public void onSurfaceCreated(GL10 gl10,EGLConfig eglConfig) {
        //光源位置の指定
        gl10.glLightfv(GL10.GL_LIGHT0,GL10.GL_POSITION, Constant.lightpos,0);
        //デプステストと光源の有効化
        gl10.glEnable(GL10.GL_DEPTH_TEST);
        gl10.glEnable(GL10.GL_LIGHTING);
        gl10.glEnable(GL10.GL_LIGHT0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10,int w,int h) {
        gl10.glViewport(0,0,w,h);
        screen = new Angle(w,h);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        //画面の初期化
        gl10.glClearColor(0.8f,0.8f,1.0f,1.0f);
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT|
                GL10.GL_DEPTH_BUFFER_BIT);

        //射影変換
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        gl10.glLoadIdentity();

        GLU.gluPerspective(gl10,
                60.0f,                        //Y方向の画角
                screen.ratio(),//アスペクト比
                0.01f,                        //ニアクリップ
                100.0f);                      //ファークリップ
        //ビュー変換
        gl10.glMatrixMode(GL10.GL_MODELVIEW);	//行列演算ターゲットの指定
        gl10.glLoadIdentity();					//行列の単位行列化
        anata.setLookAt(gl10);
        synchronized (lock) {
            list.draw(gl10);
        }

    }
    //定期処理
    public void tick(float tstep) {
        switch (scene) {
            case S_START:
                touch.setInvalid();
                anata.init();
                score.clear();
                break;
            case S_PLAY:
                if(touch.isValid()){
                    if(touch.isInLeft(screen)){
                        anata.turnLeft();
                    }else if(touch.isInRight(screen)){
                        anata.turnRight();
                    }
                    anata.speedUp(tstep);
                }else {
                    anata.speedDown(tstep);
                }
                anata.goForward(tstep);
                synchronized (lock){
                    boolean changed=false;
                    Iterator<? extends Drawable> i = list.iterator();
                    while(i.hasNext()){
                        Drawable d = i.next();
                        if(d instanceof Touchable){
                            Touchable t = (Touchable)d;
                            if(t.isContained(anata.getPostion())){
                                anata.reverseVelocity();
                                t.die();
                                i.remove();
                                score.add(10);
                                changed=true;
                            }
                        }
                    }
                    if(changed){
                        list.add(Box.createBox());
                    }
                }


                break;
        }
    }

    //タッチイベントの処理
    //タッチされた座標を取得、離れたときに—1を格納
    public View.OnTouchListener getOnTouchListener(){
        return new MyOnTouchListener();
    }

    class MyOnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            score.update();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    switch (scene) {
                        case S_START:
                            scene = Scene.S_PLAY;
                            break;
                        case S_PLAY:
                            touch.set(event.getX(),event.getY());
                            break;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    touch.setInvalid();
                    break;
            }
            return true;
        }
    }
}