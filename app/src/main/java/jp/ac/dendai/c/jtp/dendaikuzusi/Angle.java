package jp.ac.dendai.c.jtp.dendaikuzusi;

public class Angle {
    private int w;
    private int h;

    public Angle(int w, int h) {
        this.h = h;
        this.w = w;
    }

    public Angle(Angle a) {
        this.w=a.w;
        this.h=a.h;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void addW(int i) {
        w+=i;
    }

    public void addH(int i) {
        h+=i;
    }
    private static float toRadian(float angle) {
        return (float)(angle*Math.PI/180);
    }
    public Vect getVect() {
        float fw = toRadian(w);
        float fh = toRadian(h);
        return new Vect((float) (Math.cos(fw) * Math.cos(fh)),
                (float) Math.sin(fh),
                (float) (Math.sin(fw) * Math.cos(fh)));
    }

    public float ratio() {
        return (float)w/h;
    }
}
