package com.jakera.gdxtest;

import android.util.Log;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MyGdxGame extends ApplicationAdapter {
	private static final String TAG=MyGdxGame.class.getSimpleName();
	private static final int FRAME_COLS=6;
	private static final int FRAME_ROWS=5;

	SpriteBatch batch;               //类似于Canvas
	Texture img;
	Texture bg;
	TextureRegion textureRegion;     //类似于Rect,可以方便地进行截图
	Sprite sprite;
	BitmapFont font;
	Stage stage;
	Label.LabelStyle style;

	Animation walkAnimation;
	Texture walkSheet;
	TextureRegion[] walkFrames;
	TextureRegion currentFrame;
	float stateTime;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		textureRegion=new TextureRegion(img);
		sprite=new Sprite(img);
		sprite.setSize(120,120);
		//设置旋转中心
		sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
		sprite.setRotation(130);
		sprite.setPosition(800,200);
		bg = new Texture("bgmain.jpg");
		font=new BitmapFont(Gdx.files.internal("data/DragonCat.fnt"),Gdx.files.internal("data/DragonCat.png"),false);
		style=new Label.LabelStyle(font,font.getColor());
		stage=new Stage(new FitViewport(480,320));
		Gdx.input.setInputProcessor(stage);
		Label label=new Label("Hello,\n Potato",style);
		label.setPosition(200,150);
		label.setFontScale(2);
		label.setColor(Color.GREEN);
		stage.addActor(label);

		walkSheet=new Texture(Gdx.files.internal("sprite-animation.png"));
		TextureRegion[][] tmp=TextureRegion.split(walkSheet,walkSheet.getWidth()/FRAME_COLS,walkSheet.getHeight()/FRAME_ROWS);
		walkFrames=new TextureRegion[FRAME_COLS*FRAME_ROWS];
		int index=0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++]=tmp[i][j];
			}
		}
		walkAnimation=new Animation(0.025f,walkFrames);
		walkAnimation.setPlayMode(Animation.PlayMode.LOOP);

		stateTime=0f;

	}

	//会不停地调用render进行渲染
	@Override
	public void render () {
		Log.i(TAG,"我在Line67，调用render="+System.currentTimeMillis());
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime+=Gdx.graphics.getDeltaTime();
		currentFrame=walkAnimation.getKeyFrame(stateTime,true);
		batch.begin();
		batch.draw(bg,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		batch.draw(img, 0, 0);
//		batch.draw(textureRegion,400,400,100,100);
		font.draw(batch,"寻找龙猫，可爱的龙猫躲起来睡觉了\n大家都在找它......",1500,100);;
		batch.draw(currentFrame,Gdx.graphics.getWidth()-600,30);
		sprite.draw(batch);
		batch.end();
		stage.act();
		stage.draw();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		bg.dispose();
		img.dispose();
	}
}
