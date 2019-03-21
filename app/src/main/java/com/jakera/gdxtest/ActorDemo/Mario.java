package com.jakera.gdxtest.ActorDemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by jakera on 2019/3/4.
 * 马里奥游戏
 */

public class Mario extends Actor {
    public static float x;
    public static float y;
    public float statetime;

    Texture texture;
    TextureRegion currentFrame;

    public ImageButton buttonL;
    public ImageButton buttonR;

    Animation aniRight;
    Animation aniLeft;
    Animation aniIdle;

    Sound sound;

    STATE state;

    enum STATE{
        Left,Right,Idle
    };

    public Mario(float x,float y){
        this.x=x;
        this.y=y;
        this.statetime=0;
        this.show();
        state=STATE.Idle;
        sound=Gdx.audio.newSound(Gdx.files.internal("music/MarioJump.mp3"));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        statetime+= Gdx.graphics.getDeltaTime();
        this.update();
        this.check();
        batch.draw(currentFrame,x,y);
    }

    public void update(){
        if (state==STATE.Left){
            this.x-=1.5f;
            if (this.x<20) this.x=20;
        }else if (state==STATE.Right){
            this.x+=1.5f;
            if (this.x>1400) this.x=1400;
        }
        this.x=x;
    }

    public void check(){
        if (state==STATE.Left){
            currentFrame=aniLeft.getKeyFrame(statetime,true);
        }else if (state==STATE.Right){
            currentFrame=aniRight.getKeyFrame(statetime,true);
        }else if (state==STATE.Idle){
            currentFrame=aniIdle.getKeyFrame(statetime,true);
        }
    }

    public void show(){
        texture=new Texture(Gdx.files.internal("Mario/MarioAndBtn.png"));

        TextureRegion[][] spilt=TextureRegion.split(texture,64,64);
        TextureRegion[][] mirr=TextureRegion.split(texture,64,64);

        for (TextureRegion[] region1:mirr){
            for (TextureRegion region2:region1){
                region2.flip(true,false);
            }
        }
        //右
        TextureRegion[] regionR=new TextureRegion[3];
        regionR[0]=spilt[0][1];
        regionR[1]=spilt[0][2];
        regionR[2]=spilt[0][0];
        aniRight=new Animation(0.1f,regionR);

        //左
        TextureRegion[] regionL=new TextureRegion[3];
        regionL[0]=mirr[0][1];
        regionL[1]=mirr[0][2];
        regionL[2]=mirr[0][0];
        aniLeft=new Animation(0.1f,regionL);

        //空闲
        TextureRegion[] regionI=new TextureRegion[1];
        regionI[0]=spilt[0][0];

        aniIdle=new Animation(0.1f,regionI);

        buttonL=new ImageButton(new TextureRegionDrawable(spilt[1][0]),new TextureRegionDrawable(spilt[1][1]));
        buttonR=new ImageButton(new TextureRegionDrawable(mirr[1][0]),new TextureRegionDrawable(mirr[1][1]));

        buttonL.setPosition(160,20);
        buttonR.setPosition(240,20);

        buttonL.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state=STATE.Idle;
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                state=STATE.Left;
                return true;
            }
        });

        buttonR.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                state=STATE.Right;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state=STATE.Idle;
                sound.play();
                super.touchUp(event, x, y, pointer, button);
            }
        });

    }




}
