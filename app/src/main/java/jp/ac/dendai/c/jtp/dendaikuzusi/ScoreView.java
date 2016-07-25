package jp.ac.dendai.c.jtp.dendaikuzusi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
public class ScoreView extends View{
    private final Context context;
    private int score =0;

    public ScoreView(Context context) {
        super(context);
        this.context=context;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Point p = new Point();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getSize(p);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        int fontsize = (p.x-200)/20+10;
        paint.setTextSize(fontsize);
        paint.setColor(Color.WHITE);
        canvas.drawText("SCORE:" + Integer.toString(score), 10, fontsize, paint);
    }
    public void clear(){
        score =0;
    }
    public void add(int point){
        score += point;
    }
    public void update(){
        invalidate();
    }
}