package com.jakera.gdxtest.ScreenDemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.jakera.gdxtest.ActorDemo.TestActor;

/**
 * Created by jakera on 2019/3/7.
 *
 * 开始场景（欢迎界面），实现Screen接口 或者 继承 ScreenAdapter类，ScreenAdapter类
 * 实现了Screen接口的所有方法。
 * 这个场景使用LibGDX的官方Logo显示3秒钟当做是游戏的欢迎界面
 *
 * 类似Screen这样的有许多方法的接口，更多时候只需要实现其中一两个方法，往往会有一个
 * 对应的便捷空实现所有接口方法的XXAdapter类，
 * 如:ApplicationListener>>ApplicationAdapter,InputProcessor>>InputAdatper
 *
 */

public class StartScreen implements Screen {

    //为了方便与MainGame进行交互，创建Screen时将MainGame作为参数传进来
    private MainGame mainGame;
    private Texture logoTexture;
    private Stage stage;
    private TestActor logoActor;
    //渲染时间步累讲变量（当前场景被展示的时间总和）
    private float deltaSum;

    public StartScreen(MainGame mainGame){
        this.mainGame=mainGame;
        //在Screen中没有Create方法，show()方法有可能被调用多次
        //所有一般在构造方法中做一些初始化操作较好

        //创建logo的纹理，图片Logo.png的宽高为300*50
        logoTexture=new Texture(Gdx.files.internal("bgmain.jpg"));

        //使用伸展视口创建舞台
        stage=new Stage(new StretchViewport(MainGame.WORLD_WIDTH,MainGame.WORLD_HEIGHT));

        //创建logo演员
        logoActor=new TestActor(new TextureRegion(logoTexture));

        //将演员设置到舞台中心
        logoActor.setPosition(stage.getWidth()/2-logoActor.getWidth()/2,
                                stage.getHeight()/2-logoActor.getHeight()/2
                              );

        //添加演员到舞台
        stage.addActor(logoActor);

    }


    @Override
    public void show() {
        deltaSum=0;
    }

    @Override
    public void render(float v) {
        //累计渲染时间步
        deltaSum+=v;
        if (deltaSum>=3.0F){
            //开始场景展示时间超过3秒，通知MainGame切换场景（启动主游戏界面）
            if (mainGame!=null){
                mainGame.showGameScreen();
                return;
            }
        }

        //使用淡蓝色清屏
        Gdx.gl.glClearColor(0.75F,1,0.98F,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //更新舞台逻辑
        stage.act();
        //绘制舞台
        stage.draw();

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //场景被销毁时释放资源
        if (stage!=null){
            stage.dispose();
        }
        if (logoTexture!=null){
            logoTexture.dispose();
        }
    }
}
