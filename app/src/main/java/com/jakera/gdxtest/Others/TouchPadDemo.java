package com.jakera.gdxtest.Others;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by jakera on 2019/3/21.
 * 游戏摇杆
 */

public class TouchPadDemo extends ApplicationAdapter {
    Stage stage;
    Touchpad touchpad;
    Texture texture;
    Texture killer;
    SpriteBatch batch;

    TextureRegionDrawable backgroud;
    TextureRegionDrawable knobRegion;
    Touchpad.TouchpadStyle touchpadStyle;

    int speed=3;
    int x=0;
    int y=0;

    @Override
    public void create() {
        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        x=Gdx.graphics.getWidth()/2;
        y=Gdx.graphics.getHeight()/2;
        batch=new SpriteBatch();
        texture=new Texture(Gdx.files.internal("data/touchPad.png"));
        killer=new Texture(Gdx.files.internal("data/killer.png"));
        backgroud=new TextureRegionDrawable(new TextureRegion(texture,0,0,
                128,128));
        knobRegion=new TextureRegionDrawable(new TextureRegion(texture,128,0,
                128,128));
        touchpadStyle=new Touchpad.TouchpadStyle(backgroud,knobRegion);
        touchpad=new Touchpad(15,touchpadStyle);
        touchpad.setBounds(Gdx.graphics.getWidth()-touchpad.getWidth(),0,150,150);
        stage.addActor(touchpad);
    }
    public void update(){
        if (touchpad.isTouched()){
            x+=touchpad.getKnobPercentX()*speed;
            y+=touchpad.getKnobPercentY()*speed;
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();

        batch.begin();
        batch.draw(killer,x,y,50,50);
        batch.end();

        stage.act();
        stage.draw();
    }
}
