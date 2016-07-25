package jp.ac.dendai.c.jtp.dendaikuzusi;

public interface Touchable extends Drawable{
    boolean isContained(Vect v);
    void die();
    boolean isAlive();
}