package com.jakera.gdxtest.ParticleEffectDemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by jakera on 2019/3/20.
 */

public class MyInput implements InputProcessor {
    //触屏X轴坐标
    public float x;
    //触屏Y轴坐标
    public float y;

    public MyInput(float x, float y) {
        //X坐标赋值
        this.x = x;
        //Y坐标赋值
        this.y = y;
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        //将拖动粒子特效的点X轴坐标赋值给参数X
        x=i;
        //将拖动粒子特效的点Y轴坐标赋值给参数y
        y= Gdx.graphics.getHeight()-i1;
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
