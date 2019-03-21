package com.jakera.gdxtest.ParticleEffectDemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by jakera on 2019/3/20.
 */

public class ParticleEffectDemo extends ApplicationAdapter {
    //声明精灵画笔
    private SpriteBatch batch;
    //声明粒子特效
    private ParticleEffect effect;
    //声明触屏监听器
    private MyInput input;
    //粒子X轴坐标
    private float x;
    //粒子Y轴坐标
    private float y;

    @Override
    public void create() {
        //实例化精灵画笔
        batch=new SpriteBatch();
        //实例化粒子特效
        effect=new ParticleEffect();
        //加载粒子特效文件
        effect.load(Gdx.files.internal("ParticleEffect/red_firework.p"),Gdx.files.internal("ParticleEffect/"));
        //实例化触屏监听器
        input=new MyInput(x,y);
        //注册监听
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render() {
        //设置屏幕背景黑色
        Gdx.gl.glClearColor(0,0,0,0);
        //清屏
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //设置粒子特效位置
        effect.setPosition(input.x,input.y);
        //开始绘制
        batch.begin();
        //更新渲染时间
        float delta=Gdx.graphics.getDeltaTime();
        //绘制特效
        effect.draw(batch,delta);
        //结束绘制
        batch.end();
    }
}
