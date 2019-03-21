package com.jakera.gdxtest.WidgetDemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;


/**
 * Created by jakera on 2019/3/4.
 * Image演员的使用示例
 */

public class ImageDemo extends ApplicationAdapter {
    Stage stage;
    Image victoriaImage;
    TextureRegion region;
    Texture tex;

    @Override
    public void create() {
        super.create();
        tex=new Texture(Gdx.files.internal("badlogic.jpg"));
        region=new TextureRegion(tex,0,0,512,512);
        victoriaImage=new Image(region);
        victoriaImage.setColor(Color.GREEN);           //给图片像素涂上颜色
        victoriaImage.setScale(0.5f);
        victoriaImage.setPosition(230,40);
        victoriaImage.setOrigin(0,0); //旋转中心
        victoriaImage.setRotation(60);                 //逆时针旋转
        victoriaImage.setSize(512,512);
        stage=new Stage(new StretchViewport(480,320));//图片会被斜着拉开
        Gdx.input.setInputProcessor(stage);
        stage.addActor(victoriaImage);
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
