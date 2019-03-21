package com.jakera.gdxtest.MoveBgDemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by jakera on 2019/3/15.
 */

public class MoveBgTestDemo extends ApplicationAdapter {
    private MoveBgStage stage;

    @Override
    public void create() {
        super.create();
        stage=new MoveBgStage();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act();
    }

    @Override
    public void dispose() {
        if (stage!=null){
            stage.dispose();
        }
    }
}
