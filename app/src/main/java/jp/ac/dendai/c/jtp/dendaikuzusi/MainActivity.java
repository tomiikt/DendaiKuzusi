package jp.ac.dendai.c.jtp.dendaikuzusi;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
public class MainActivity extends Activity {
    private MyGLView glView;
    private ScoreView scoreView;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
    @Override
    public void onStart() {
        super.onStart();
        scoreView = new ScoreView(this);
        glView = new MyGLView(this, scoreView);
        setContentView(glView);
        addContentView(scoreView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }
    @Override
    public void onResume() {
        super.onResume();
        glView.onResume();
        glView.threadstart();
    }
    @Override
    public void onPause() {
        super.onPause();
        glView.onPause();
        glView.threadpause();
    }
}