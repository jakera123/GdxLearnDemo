package com.jakera.gdxtest.Others;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by jakera on 2019/3/12.
 */

public class AssetManagerDemo extends ApplicationAdapter {

    //资源在assets文件夹中的相对路径
    private static final String BADLOGIC="badlogic.jpg";
    private static final String LOGO="bgmain.jpg";

    private SpriteBatch batch;
    private AssetManager manage;
    private Texture badlogicTexture;
    private Texture logoTexture;

    @Override
    public void create() {
        batch=new SpriteBatch();

        //创建资源管理器
        manage=new AssetManager();

        //加载资源
        manage.load(BADLOGIC,Texture.class);
        manage.load(LOGO,Texture.class);

        //阻塞线程，等待资源加载完毕
        manage.finishLoading();

        //加载完毕后获取资源实例
        badlogicTexture=manage.get(BADLOGIC,Texture.class);
        logoTexture=manage.get(LOGO,Texture.class);
    }

    @Override
    public void render() {
        //白色清屏
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.draw(badlogicTexture,0,0);
        batch.draw(logoTexture,10,300);

        batch.end();
    }


    @Override
    public void dispose() {
        //当应用退出时释放资源
        if (manage!=null){
            /**
             * 释放所有资源，并销毁AssetManager
             *
             * 注意：本例中的Texture通过AssetMAnager进行管理，
             * 因此这里不需要再调用Texture的dispose()方法
             */
            manage.dispose();
        }
    }
}
