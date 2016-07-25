package jp.ac.dendai.c.jtp.dendaikuzusi;

public interface Constant {
    float MaxV = 0.2f;
    float EYEDIS = 2.0f;
    float boxsize = 2.0f;
    float[] red = {1.0f, 0.0f, 0.0f, 1.0f};
    float[] blue = {0.0f, 0.0f, 1.0f, 1.0f};
    float[] yellow = {1.0f, 1.0f, 0.0f, 1.0f};
    float[] green = {0.0f, 0.8f, 0.0f, 1.0f};
    float[] brown = {0.8f, 0.4f, 0.0f, 1.0f};
    float[] lightpos = {10.0f, 10.0f, 10.0f, 0.0f};
    float[][] colorList = {red, blue, yellow};
    long tic = 10;
    float acc = 0.0005f;
    float bacc = 0.0005f;
    float YukaSIZE = 100;
    float YukaY = -5;
    float YukaZERO = 0;
    float AreaRatio = 0.9f;
    int turnAngle = 2;
}
