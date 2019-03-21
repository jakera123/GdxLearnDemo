package com.jakera.gdxtest.MoveBgDemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jakera.gdxtest.Utils.Constant;

/**
 * Created by jakera on 2019/3/15.
 * 一个连续不断的背景移动
 */

public class BackgoundActor extends Actor {

    private final TextureRegion textureregion;
    private Rectangle textureRegionBounds1;
    private Rectangle textureRegionBounds2;
    private int speed=100;

    public BackgoundActor() {
        Texture texture=new Texture(Gdx.files.internal("GameBg.jpg"));
        textureregion=new TextureRegion(texture);
        textureRegionBounds1=new Rectangle(0- Constant.APP_WIDTH/2,0,Constant.APP_WIDTH,Constant.APP_HEIGHT);
        textureRegionBounds2=new Rectangle(Constant.APP_WIDTH/2,0,Constant.APP_WIDTH,Constant.APP_HEIGHT);
    }


    @Override
    public void act(float delta) {
        if (leftBoundsReached(delta)){
            resetBounds();
        }else {
            updateXBounds(-delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureregion,textureRegionBounds1.x,textureRegionBounds1.y,Constant.APP_WIDTH,Constant.APP_HEIGHT);
        batch.draw(textureregion,textureRegionBounds2.x,textureRegionBounds2.y,Constant.APP_WIDTH,Constant.APP_HEIGHT);
    }

    private boolean leftBoundsReached(float delta){
        return (textureRegionBounds2.x-(delta*speed))<=0;
    }

    private void updateXBounds(float delta){
        textureRegionBounds1.x+=delta*speed;
        textureRegionBounds2.x+=delta*speed;
    }

    private void resetBounds(){
        textureRegionBounds1=textureRegionBounds2;
        textureRegionBounds2=new Rectangle(Constant.APP_WIDTH,0,Constant.APP_WIDTH,Constant.APP_HEIGHT);
    }
}
