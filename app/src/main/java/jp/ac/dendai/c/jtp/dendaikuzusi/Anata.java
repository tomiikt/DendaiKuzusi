package jp.ac.dendai.c.jtp.dendaikuzusi;


import android.opengl.GLU;

import javax.microedition.khronos.opengles.GL10;

public class Anata {
    private Vect spec;
    private Angle specAngle;
    private Angle eyeAngle;
    private float velocity;
    void setLookAt(GL10 gl10){
        eyeAngle = new Angle(specAngle);
        Vect eye = new Vect(spec);
        Vect vect = eyeAngle.getVect();
        vect .mul(Constant.EYEDIS);
        eye.sub(vect);
        GLU.gluLookAt(gl10,
                eye.getX(), eye.getY(), eye.getZ(),
                spec.getX(), spec.getY(), spec.getZ(),
                0.0f, 1.0f, 0.0f);
    }

    public void init(){
        velocity=0;
        setAngle(new Angle(-90, 0));
        setVect(new Vect(0, 0, 50));
    }
    public void setAngle(Angle angle) {
        specAngle = angle;
    }
    public void speedUp(float tstep){
        velocity+=Constant.acc*tstep;
        if(velocity>Constant.MaxV){
            velocity=Constant.MaxV;
        }
    }
    public void speedDown(float tstep){
        float oldvelocity=velocity;
        if(velocity>0) {
            velocity -= Constant.bacc * tstep;
        }else if(velocity <0){
            velocity += Constant.bacc * tstep;
        }
        if(velocity*oldvelocity<0){
            velocity=0;
        }
    }

    public void setVect(Vect vect) {
        spec=vect;
    }

    public void turnLeft() {
        specAngle.addW(-Constant.turnAngle);
    }

    public void turnRight() {
        specAngle.addW(Constant.turnAngle);
    }

    public void goForward(float tstep) {
        Vect d = eyeAngle.getVect();
        d.mul(velocity * tstep);
        spec.add(d);
    }

    public Vect getPostion() {
        return spec;
    }

    public void reverseVelocity() {
        velocity=-velocity;
    }
}


