package com.jakera.gdxtest;

import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.jakera.gdxtest.ActionDemo.TestActionDemo;
import com.jakera.gdxtest.ActorDemo.MarioGame;
import com.jakera.gdxtest.ActorDemo.TestGroupDemo;
import com.jakera.gdxtest.Box2dDemo.MoveBody;
import com.jakera.gdxtest.CameraDemo.OrthographicCameraController;
import com.jakera.gdxtest.MoveBgDemo.MoveBgTestDemo;
import com.jakera.gdxtest.Others.AssetManagerDemo;
import com.jakera.gdxtest.Others.CrashInspectDemo;
import com.jakera.gdxtest.Others.NetAccessDemo;
import com.jakera.gdxtest.ParticleEffectDemo.ParticleEffectDemo;
import com.jakera.gdxtest.ScreenDemo.MainGame;
import com.jakera.gdxtest.StageDemo.MarioGameStageDemo;
import com.jakera.gdxtest.TextureAltasDemo.TextureAtasDemo;
import com.jakera.gdxtest.ViewportDemo.ViewportTestDemo;
import com.jakera.gdxtest.WidgetDemo.ButtonDomo;
import com.jakera.gdxtest.WidgetDemo.CheckBoxDemo;
import com.jakera.gdxtest.WidgetDemo.TextFieldDemo;

public class MainActivity extends AndroidApplication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new OrthographicCameraController(), config);
    }
}
