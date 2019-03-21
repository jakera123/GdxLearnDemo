package com.jakera.gdxtest.ViewportDemo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by jakera on 2019/3/7.
 */

public class TestViewportActor extends Actor {
    private TextureRegion region;
    public TestViewportActor(TextureRegion region){
        super();
        this.region=region;
        setSize(this.region.getRegionWidth(),this.region.getRegionHeight());
    }
    public TextureRegion getRegion(){
        return region;
    }
    public void setRegion(TextureRegion region){
        this.region=region;
        setSize(this.region.getRegionWidth(),this.region.getRegionHeight());;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (region==null||!isVisible()){
            return;
        }
        batch.draw(region,
                getX(),getY(),
                getOriginX(),getOriginY(),
                getWidth(),getHeight(),
                getScaleX(),getScaleY(),
                getRotation());
    }
}
