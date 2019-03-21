package com.jakera.gdxtest.WidgetDemo;

import android.util.Log;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by jakera on 2019/3/4.
 */

public class ButtonDomo extends ApplicationAdapter {
    private final static String TAG=ButtonDomo.class.getSimpleName();
    Stage stage;
    TextureRegionDrawable right;
    TextureRegion buttonRight;
    Texture tex;
    ImageButton button;

    @Override
    public void create() {
        super.create();
        tex=new Texture(Gdx.files.internal("Button/btn_arrow.png"));
        buttonRight=new TextureRegion(tex);
        right=new TextureRegionDrawable(buttonRight);
        button=new ImageButton(right);
        button.setPosition(100,100);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Log.i(TAG,"我在line41,当前事件="+event.getKeyCode());
            }
        });
        stage=new Stage(new FitViewport(480,320));
        Gdx.input.setInputProcessor(stage);
        stage.addActor(button);
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
