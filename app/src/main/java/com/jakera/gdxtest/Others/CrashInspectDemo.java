package com.jakera.gdxtest.Others;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by jakera on 2019/3/12.
 */

public class CrashInspectDemo extends ApplicationAdapter {

    public static final float WORLD_WIDTH=480;
    public static final float WORLD_HEIGHT=800;

    private Stage stage;

    private Texture badlogicTexture;
    private Texture logoTexture;

    private Image badlogicActor;
    private Image logoActor;

    //演员的包围矩形
    private final Rectangle badlogicRect=new Rectangle();
    private final Rectangle logoRect=new Rectangle();

    @Override
    public void create() {
        //使用伸展视口（StretchViewport）创建舞台
        stage=new Stage(new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT));

        //创建纹理
        badlogicTexture=new Texture(Gdx.files.internal("badlogic.jpg"));
        logoTexture=new Texture(Gdx.files.internal("bgmain.jpg"));

        badlogicActor=new Image(badlogicTexture);
        logoActor=new Image(logoTexture);

        badlogicActor.setPosition(stage.getWidth()/2-badlogicActor.getWidth()/2,
                stage.getHeight()/2-badlogicActor.getHeight()/2);
        logoActor.setPosition(0,0);

        stage.addActor(badlogicActor);
        stage.addActor(logoActor);

        //给logo演员附加一个动作
        MoveToAction action= Actions.moveTo(150,700,5.0F);
        logoActor.addAction(action);
    }

    @Override
    public void render() {
        //白色清屏
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //更新舞台逻辑
        stage.act();

        //碰撞检测
        checkCollision();

        //绘制舞台
        stage.draw();
    }

    /**
     * 碰撞检测
     */
    private void checkCollision(){
        /**
         * 获取演员的包围矩形
         *
         * 注意：如果对演员进行了缩放旋转等变换，需要获取的是变换后视
         * 觉上的包围矩形，后续的引擎封装中将详细介绍
         */
        badlogicRect.set(badlogicActor.getX(),
                badlogicActor.getY(),
                badlogicActor.getWidth(),
                badlogicActor.getHeight()
                );
        logoRect.set(logoActor.getX(),
                logoActor.getY(),
                logoActor.getWidth(),
                logoActor.getHeight());

        //判断两个演员是否碰撞，如果碰撞，则将badlogicActor为半透明
        if (badlogicRect.overlaps(logoRect)){
            badlogicActor.getColor().a=0.5F;
        }else {
            badlogicActor.getColor().a=1.0F;
        }
    }

    @Override
    public void dispose() {
        //当应用退出时释放资源
        if (stage!=null){
            stage.dispose();
        }
        if (badlogicTexture!=null){
            badlogicTexture.dispose();
        }
        if (logoTexture!=null){
            logoTexture.dispose();
        }
    }
}
