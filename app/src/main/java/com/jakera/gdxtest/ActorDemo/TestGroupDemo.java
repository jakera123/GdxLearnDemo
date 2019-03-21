package com.jakera.gdxtest.ActorDemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by jakera on 2019/3/7.
 */

public class TestGroupDemo extends ApplicationAdapter {
    private Texture texture;
    private Stage stage;
    private Group group;
    private TestActor actor1;
    private TestActor actor2;

    @Override
    public void create() {
        //创建纹理
        texture=new Texture(Gdx.files.internal("badlogic.jpg"));
        //创建舞台，舞台宽高默认为屏幕宽高
        stage=new Stage();
        //创建演员组
        group=new Group();
        //设置group在舞台中的位置
        group.setPosition(50,100);
        //添加group到舞台（group本身也是一个演员）
        stage.addActor(group);
        //创建第一个演员，并设置属性
        actor1=new TestActor(new TextureRegion(texture));
        actor1.setScale(0.25f);
        actor1.setPosition(0,0);   //位置设置到group的左下角
        //创建第二个演员，并设置属性
        actor2=new TestActor(new TextureRegion(texture));
        actor2.setScale(0.25f);
        actor2.setPosition(100,200);
        actor2.setRotation(45);   //逆时针旋转45度
        //添加演员到组中
        group.addActor(actor1);
        group.addActor(actor2);
        //还可以设置演员的绘制顺序（ZIndex属性）
        /**
         * ZIndex 属性说明
         *
         * 当组中有许多演员时，如果某些演员有重叠部分，往往需要规定演员的绘制顺序，
         * 在Actor中使用ZIndex属性来表示绘制顺序，ZIndex值越大越后绘制，即显示越靠前。
         *
         * ZIndex属性值其实对应的是Group中的演员数组对象中演员所在的索引位置（添加到Group
         * 中的演员的默认添加到数组的最后位置）。绘制时Group将遍历数组依次绘制演员，则越靠后
         * （ZIndex/index越大）的元素越后绘制（即显示越靠前）
         *
         * 因此，必须先将Actor添加到Group（或Stage，因为Stage也是通过Group管理其中的演员）中之
         * 后，否则ZIndex属性没有意义
         *
         * 获取演员的索引，如果actor没有添加到Group/Stage中，将返回-1
         * int index=actor.getZIndex();
         *
         * 设置演员的索引（实际上是将数组中的演员元素移动到指定的索引位置，可能会引起其他演员的
         * ZIndex值跟着改变）
         *actor.setZIndex(int index);
         */
    }

    @Override
    public void render() {
        //黑色清屏
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //更新舞台逻辑
        stage.act();
        //绘制舞台
        stage.draw();
    }

    @Override
    public void dispose() {
        //释放资源
        if (texture!=null){
            texture.dispose();
        }
        if (stage!=null){
            stage.dispose();
        }
    }
}
