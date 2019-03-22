package com.jakera.gdxtest.WidgetDemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by jakera on 2019/3/21.
 * 弹出对话框Demo
 */

public class PopupWindowDemo extends ApplicationAdapter {
    SpriteBatch batch;
    Texture texture;
    ImageButton Btn_A_OK;
    ImageButton Btn_B_Cancel;
    ImageButton Btn_SHOW;
    Stage stage;
    BitmapFont font;
    Window dialogWindow;

    @Override
    public void create() {
        stage=new Stage(new ScreenViewport());
        batch=new SpriteBatch();
        font=new BitmapFont();
        setButton();
        setWindow();
        setBtnListener();

        stage.addActor(Btn_SHOW);
        Gdx.input.setInputProcessor(stage);
    }
    public void setButton(){
        texture=new Texture(Gdx.files.internal("Widget/TestWindowButton.png"));

        TextureRegion[][] spilt=TextureRegion.split(texture,64,64);

        TextureRegion[] regionBtn=new TextureRegion[6];
        //显示
        regionBtn[0]=spilt[0][0];
        regionBtn[1]=spilt[0][1];
        //确认
        regionBtn[2]=spilt[0][2];
        regionBtn[3]=spilt[0][3];
        //取消
        regionBtn[4]=spilt[1][0];
        regionBtn[5]=spilt[1][1];

        TextureRegionDrawable Btn_SHOW_UP=new TextureRegionDrawable(regionBtn[0]);
        TextureRegionDrawable Btn_SHOW_DOWN=new TextureRegionDrawable(regionBtn[1]);

        TextureRegionDrawable Btn_A_UP=new TextureRegionDrawable(regionBtn[2]);
        TextureRegionDrawable Btn_A_DOWN=new TextureRegionDrawable(regionBtn[3]);

        TextureRegionDrawable Btn_B_UP=new TextureRegionDrawable(regionBtn[4]);
        TextureRegionDrawable Btn_B_DOWN=new TextureRegionDrawable(regionBtn[5]);

        Btn_SHOW=new ImageButton(Btn_SHOW_UP,Btn_SHOW_DOWN);
        Btn_A_OK=new ImageButton(Btn_A_UP,Btn_A_DOWN);
        Btn_B_Cancel=new ImageButton(Btn_B_UP,Btn_B_DOWN);
    }
    public void setWindow(){
        TextureRegionDrawable WindowDrable=new TextureRegionDrawable(new TextureRegion(new Texture("Widget/Window.png")));
        com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle style=new com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle(font, Color.RED,WindowDrable);
        dialogWindow=new Window("Game",style);
        float dialogWidth=Gdx.graphics.getWidth()/1.5f;
        float dialogHeight=Gdx.graphics.getHeight()/1.5f;
        dialogWindow.setWidth(dialogWidth);
        dialogWindow.setHeight(dialogHeight);
        float dialogX=(Gdx.graphics.getWidth()-dialogWidth)/2;
        float dialogY=(Gdx.graphics.getHeight()-dialogHeight)/2;
        dialogWindow.setPosition(dialogX,dialogY);
        dialogWindow.setMovable(true);

        Btn_A_OK.setPosition(Gdx.graphics.getWidth()/10,dialogHeight/10);
        Btn_B_Cancel.setPosition(Gdx.graphics.getWidth()/3,dialogHeight/10);

        dialogWindow.addActor(Btn_A_OK);
        dialogWindow.addActor(Btn_B_Cancel);
    }
    public void setBtnListener(){
        Btn_SHOW.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.addActor(dialogWindow);
                return true;
            }
        });
        Btn_A_OK.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });
        Btn_B_Cancel.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                dialogWindow.remove();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.draw(batch,"Touch The Button!",100,200);
        batch.end();
        stage.act();
        stage.draw();
    }
}
