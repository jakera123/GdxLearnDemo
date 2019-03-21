package com.jakera.gdxtest.MoveBgDemo;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.jakera.gdxtest.Utils.Constant;

/**
 * Created by jakera on 2019/3/15.
 */

public class MoveBgStage extends Stage {
    private static final int VIEWPORT_WIDTH= Constant.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT=Constant.APP_HEIGHT;

    public MoveBgStage() {
        super(new ScalingViewport(Scaling.stretch,VIEWPORT_WIDTH,VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH,VIEWPORT_HEIGHT)));
        setupBg();
    }
    private void setupBg(){
        addActor(new BackgoundActor());
    }

    @Override
    public void act() {
        super.act();
    }
}
