package jp.ac.dendai.c.jtp.dendaikuzusi;


public class Position {
    private float x;
    private float y;
    private boolean valid;
    public Position(){
        x=-1;
        y=-1;
        valid=false;
    }
    public void setX(float _x){
        x=_x;
    }
    public void setY(float _y) {
        y = _y;
    }
    public void setInvalid() {
        valid = false;
    }
    public boolean isValid() {
        return valid;
    }

    public boolean isInLeft(Angle screen) {
        return x < (float)screen.getW() / 3;
    }

    public boolean isInRight(Angle screen) {
        return x > (float)screen.getW() * 2 / 3;
    }
    public void set(float _x, float _y) {
        x=_x;
        y=_y;
        valid=true;
    }
}
