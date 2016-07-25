package jp.ac.dendai.c.jtp.dendaikuzusi;


public class Vect {
    private float x;
    private float y;
    private float z;

    public Vect(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vect(Vect v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void mul(float e) {
        x*=e;
        y*=e;
        z*=e;
    }

    public void sub(Vect v) {
        x-=v.x;
        y-=v.y;
        z-=v.z;
    }

    public void add(Vect v) {
        x+=v.x;
        y+=v.y;
        z+=v.z;
    }
    public float squareOfDistance(Vect v){
        return sq(v.getX()-x)+sq(v.getY()-y)+sq(v.getZ()-z);
    }
    private float sq(float v) {
        return v*v;
    }
}
