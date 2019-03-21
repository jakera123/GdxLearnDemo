package com.jakera.gdxtest.StageDemo;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by jakera on 2019/3/4.
 */

public class StartStage extends Stage {
    private final static String TAG=StartStage.class.getSimpleName();
    Texture texture;
    TextureRegion startRegion;
    Image startImage;
    Image newGameBtn;
    TextureRegion newGameRegion;

    Music musicBg;

    public StartStage(){
        super();
        init();
    }

    public void init(){
        texture=new Texture(Gdx.files.internal("Mario/StageStart.jpg"));
        startRegion=new TextureRegion(texture,0,0,800,440);
        startImage=new Image(startRegion);
        startImage.setSize(2300,1700);                         //调整背景大小

        newGameRegion=new TextureRegion(texture,854,0,100,30);
        newGameBtn=new Image(newGameRegion);
        newGameBtn.setPosition(220,1320);
        newGameBtn.setScale(3.4f);

        this.addActor(startImage);
        this.addActor(newGameBtn);

        newGameBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Constants.Stageflag=Constants.GameStageOn;
                return true;
            }
        });

        musicBg=Gdx.audio.newMusic(Gdx.files.internal("music/MarioSeaBg.mp3"));
        musicBg.play();
        musicBg.setLooping(true);
        musicBg.setVolume(15);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
