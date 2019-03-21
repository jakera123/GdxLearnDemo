package com.jakera.gdxtest.StageDemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by jakera on 2019/3/5.
 */

public class MarioGameStageDemo extends ApplicationAdapter {
    GameStage gameStage;
    StartStage startStage;
    StoreStage storeStage;

    @Override
    public void create() {
        super.create();
        gameStage=new GameStage();
        startStage=new StartStage();
        storeStage=new StoreStage();
    }

    public void selectStageRender(){
        if (Constants.Stageflag==Constants.StartStageOn){
            Gdx.input.setInputProcessor(startStage);
            startStage.act();
            startStage.draw();
            startStage.musicBg.play();
        }else if (Constants.Stageflag==Constants.GameStageOn){
            Gdx.input.setInputProcessor(gameStage);
            gameStage.act();
            gameStage.draw();
            startStage.musicBg.pause();
        }else if (Constants.Stageflag==Constants.StoreStageOn){
            Gdx.input.setInputProcessor(storeStage);
            storeStage.act();
            storeStage.draw();
            startStage.musicBg.pause();
        }
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        selectStageRender();
    }
}
