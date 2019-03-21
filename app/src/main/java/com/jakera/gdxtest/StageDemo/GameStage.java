package com.jakera.gdxtest.StageDemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jakera.gdxtest.ActorDemo.Mario;

/**
 * Created by jakera on 2019/3/4.
 */

public class GameStage extends Stage {
    Image backdrop;
    Mario mario;
    Texture texture;
    TextureRegion backdropRegion;
    Image mushroom;
    TextureRegion mushRegion;
    public GameStage(){
        this.init();
    }
    public void init(){
        texture=new Texture(Gdx.files.internal("Mario/StageShop.jpg"));
        backdropRegion=new TextureRegion(texture,512,256,512,128);
        backdrop=new Image(backdropRegion);
        mushRegion=new TextureRegion(texture,204,0,102,75);
        mushroom=new Image(mushRegion);

        mario=new Mario(100,190);
        backdrop.setPosition(0,170);
        mushroom.setPosition(1490,60);
        mushroom.setScale(4);

        this.addActor(backdrop);
        this.addActor(mario);
        this.addActor(mario.buttonL);
        this.addActor(mario.buttonR);
        this.addActor(mushroom);
        mushroom.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Constants.Stageflag=Constants.StoreStageOn;
                return true;
            }
        });
    }
}
