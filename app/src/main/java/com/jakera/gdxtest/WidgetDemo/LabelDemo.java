package com.jakera.gdxtest.WidgetDemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by jakera on 2019/3/4.
 * label演员的示例
 */

public class LabelDemo extends ApplicationAdapter {
    Stage stage;
    Label.LabelStyle style;
    BitmapFont font;

    @Override
    public void create() {
        super.create();
        font =new BitmapFont(Gdx.files.internal("data/DragonCat.fnt"),
                Gdx.files.internal("data/DragonCat.png"),false);
        style=new Label.LabelStyle(font,font.getColor());
        stage=new Stage(new FitViewport(480,320));
        Gdx.input.setInputProcessor(stage);
        Label label1=new Label("可爱的龙猫",style);    //仅显示由工具生成的DragonCat.png里的文字
        label1.setPosition(50,150);
        label1.setFontScale(1);
        label1.setColor(Color.BLUE);
        stage.addActor(label1);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}
