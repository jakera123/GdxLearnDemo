package com.jakera.gdxtest.ActorDemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by jakera on 2019/3/4.
 */

public class MarioGame extends ApplicationAdapter {
    Stage stage;
    Mario mario;
    Image image;

    @Override
    public void create() {
        super.create();
        image=new Image(new Texture(Gdx.files.internal("Mario/MarioBg.jpg")));
        image.setPosition(0,170);

        stage=new Stage(new FitViewport(480,320));
        mario=new Mario(100,190);
        Gdx.input.setInputProcessor(stage);

        stage.addActor(image);
        stage.addActor(mario);
        stage.addActor(mario.buttonL);
        stage.addActor(mario.buttonR);
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}
