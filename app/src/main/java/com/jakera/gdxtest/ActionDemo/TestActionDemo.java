package com.jakera.gdxtest.ActionDemo;

import android.util.Log;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AfterAction;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by jakera on 2019/3/11.
 * tips:必须重写render方法，否则onCreate不会被执行
 */

public class TestActionDemo extends ApplicationAdapter {
    private static final String TAG=TestActionDemo.class.getSimpleName();

    //视口世界的宽高统一使用480*800，并统一使用伸展视口（StretchViewport）
    public static final float WORLD_WIDTH=480;
    public static final float WORLD_HEIGHT=800;

    private Texture texture;
    private Stage stage;
    private TestActionActor actor;

    @Override
    public void create() {
        Log.i(TAG,"我在Line32,onCreate");
        //设置Log输出级别
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        //创建纹理，badlogic.jpg图片的宽高为256*256
        texture=new Texture(Gdx.files.internal("badlogic.jpg"));
        //使用伸展视口（StretchViewport）创建舞台
        stage=new Stage(new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT));
        //创建演员
        actor=new TestActionActor(new TextureRegion(texture));
        //添加演员到舞台
        stage.addActor(actor);
        /**
         * 动作演示
         *
         * 有些时候动作需要频繁使用，如果每次要使用动作都重新创建一个对象，会消耗性能
         * LibGDX中对动作实例使用了对象池进行了缓存，使用完一个动作对象后不会直接让系统回
         * 收对象，而是放到对象池中缓存，待下次重复使用。LibGDX中所有动作实例的获取均通过
         * Actions工具类中的静态方法获取（Actions中实现了对象池缓存）
         *
         * 获取到一个动作对象后附加在演员身上执行（addAction），动作执行完后会自动从演员身上
         * 移除并放回对象池中缓存。当然在动作还没有执行完时也可在中途手动移除（removeAction）.
         *
         * 下面对每一种动作的演示放在单独方法中，按顺序打开相应测试方法的注释进行测试（打开一个
         * 方法的注释后必须要注释掉其他测试方法，否则多个测试同时执行看不出效果）
         */

        //1.移动动作
//        testMoveToAction();

        //2、移动动作（相对）
//        testMoveByAction();

        //3、旋转动作
//        testRotateToAction();

        //4、旋转动作（相对）
//        testRotateByAction();

        //5、缩放动作
//        testScaleToAction();

//        6、缩放动作（相对）
//        testScaleByAction();

//      7、尺寸改变动作
//        testSizeToAction();

//        8、尺寸改变动作（相对）
//        testSizeByAction();

//        9、透明度动作
//        testAlphaAction();

//        10、并行动作：同时 移动，缩放，旋转
//          testParallelAction();

//        11、顺序动作（包含了延时动作的演示）：先延时，然后移动，再旋转并缩放
//          testSequenceAction();

//        12、重复动作： 重复 缩小，放大
//        testRepeatAction();

//        13、适当时机执行自己的代码，与顺序动作一起使用可用于监听某个动作的完成
//        testRunnableAction();

//        14、After动作：可用于监听演员动作的执行完成
          testAfterAction();
    }
    /**
     * 1、移动动作
     */
    private void testMoveToAction(){
        Log.i(TAG,"我在line65,testMoveToAction");
        //设置演员初始化位置
        actor.setPosition(0,0);

        //获取一个MoveTo动作，3秒内移动到（150，300）的位置
        MoveToAction action= Actions.moveTo(150,300,3.0F);

        //将动作附加在演员身上，执行动作
        actor.addAction(action);

        /**
         * 动作执行原理（醒看Actor和相应Action的源码）:
         *
         * 实际上动作添加到演员身上后，动作被存放到一个数组中，然后在更新演员逻辑的
         * actor.act()方法中遍历存放动作的数组，对每一个动作根据时间步delta改变演员
         * 相应的状态属性值，然后在绘制演员的actor.draw()方法中绘制演员时使用新的状
         * 态属性值绘制，和上一帧相比，就显得演员被“动”起来了。
         */
    }

    /**
     * 2、移动动作（相对）
     */
    private void testMoveByAction(){
        //演员初始化位置设置显示到舞台中心
        actor.setPosition(actor.getStage().getWidth()/2-actor.getWidth()/2,
                actor.getStage().getHeight()/2-actor.getHeight()/2);
        //获取一个MoveBy动作
        //2秒内，在演员原位置基础上，水平方向移动100，竖直方向移动-200
        MoveByAction action=Actions.moveBy(100,-200,2.0F);

        //将动作附在演员身上，执行动作
        actor.addAction(action);
    }

    /**
     * 3、旋转动作
     */
    private void testRotateToAction(){
        //设置演员显示到舞台中心
        actor.setPosition(actor.getStage().getWidth()/2-actor.getWidth()/2,
                actor.getStage().getHeight()/2-actor.getWidth()/2);

        //缩放和旋转支点设置到演员中心
        actor.setOrigin(actor.getWidth()/2,actor.getHeight()/2);

        //设置演员的初始化角度为-90度（逆时针为正，顺时针为负）
        actor.setRotation(-90);

        //获取一个RotateTo动作，2秒内从原角度旋转到-270度（最终角度为-270度）
        RotateToAction action=Actions.rotateTo(-270,2.0F);

        //执行动作
        actor.addAction(action);
    }

    private void testRotateByAction(){
        //设置演员显示到舞台中心
        actor.setPosition(actor.getStage().getWidth()/2-actor.getWidth()/2,
                actor.getStage().getHeight()/2-actor.getHeight()/2);

        //缩放和旋转支点设置到演员中心
        actor.setOrigin(actor.getWidth()/2,actor.getHeight()/2);

        //设置演员的初始角度为-90度（逆时针为正，顺时针为负）
        actor.setRotation(-90);

        //获取一个RotateBy动作，2秒内,从原角度基础上增加45度（最终角度为-90+45=-45度）
        RotateByAction action=Actions.rotateBy(45,2.0F);

        //执行动作
        actor.addAction(action);
    }

    /**
     * 5、缩放动作
     */
    private void testScaleToAction(){
        //设置演员显示到舞台中心
        actor.setPosition(actor.getStage().getWidth()/2-actor.getWidth()/2,
                actor.getStage().getHeight()/2-actor.getHeight()/2);

        //缩放和旋转支点设置到演员中心
        actor.setOrigin(actor.getWidth()/2,actor.getHeight()/2);;

        //设置演员的水平和竖直方向初始缩放比分别为0.5和2.0
        actor.setScale(0.5F,2.0F);

        //获取一个ScaleTo动作，2秒内水平和竖直方向缩放比从原缩放比分别缩
        //放到1.0和1.0
        //最终水平和竖直方向缩放比分别为1.0和1.0
        ScaleToAction action=Actions.scaleTo(1.0F,1.0F,2.0F);

        //执行动作
        actor.addAction(action);
    }

    /**
     * 6、缩放动作（相对）
     */
    private void testScaleByAction(){
        //设置演员显示到舞台中心
        actor.setPosition(actor.getStage().getWidth()/2-actor.getWidth()/2,
                actor.getStage().getHeight()/2-actor.getHeight()/2);

        //缩放和旋转支点设置到演员中心
        actor.setOrigin(actor.getWidth()/2,actor.getHeight()/2);

        //设置演员的水平和竖直方向初始化缩放比分别为0.5和0.5
        actor.setScale(0.5F,0.5F);

        //获取一个ScaleBy动作，2秒内水平和竖直方向缩放比从 原缩放比基础上 分别加上 0.5和0.5（正数表示增加，负数表示减少）
        //ScaleBy是对原缩放值单纯的数值相加，最终水平和竖直方向的缩放比均为:0.5+0.5=1.0
        ScaleByAction action=Actions.scaleBy(0.5F,0.5F,2.0F);

        //执行动作
        actor.addAction(action);

    }

    /**
     * 7、尺寸改变动作
     */
    private void testSizeToAction(){
        //设置演员初始化位置
        actor.setPosition(0,0);
        //获取一个SizeTo动作，2秒内从原来尺寸变到宽为150，高为300
        SizeToAction action=Actions.sizeTo(150,300,2.0F);

        //执行动作
        actor.addAction(action);

        /**
         * Scale和Size的区别：
         *
         * Size指的是演员的宽高，Scale指的是演员相对于Size的缩放比
         * 如果Scale为0.5
         * 当Size变为256时，绘制到屏幕上的大小变为256*0.5=128
         * 当Size变为512时，绘制到屏幕上折大小变为512*0.5=256
         */
    }

    /**
     * 8、尺寸改变动作（相对）
     */
    private void testSizeByAction(){
        //设置演员初始化位置
        actor.setPosition(0,0);

        //演员初始宽高为图片的宽高256*256

        //获取一个SizeTo动作，2秒内宽高从原来基础上分别增加150，300
        //最终宽度为：256+150=406
        //最终高度为：256+300=556
        SizeByAction action=Actions.sizeBy(150,300,2.0F);

        //执行动作
        actor.addAction(action);
    }

    /**
     * 9、透明度动作
     */
    private void testAlphaAction(){
        //设置演员初始化位置
        actor.setPosition(0,0);

        //设置演员初始alpha值为1（完全不透明，默认就是为1）
        actor.getColor().a=1.0F;

        //获取一个Alpha动作，5秒内alpha变为0（完全透明）
        AlphaAction action=Actions.alpha(0.0F,5.0F);

        //执行动作
        actor.addAction(action);
    }

    /**
     * 10、并行动作：同时 移动，缩放，旋转
     */
    private void testParallelAction(){
        //设置演员初始化状态
        actor.setPosition(0,0);
        actor.setScale(0.5F,0.5F);
        actor.setRotation(0);

        //缩放和旋转支汇款后设置到演员中心
        actor.setOrigin(actor.getWidth()/2,actor.getHeight()/2);

        //移动动作
        MoveToAction moveTo=Actions.moveTo(150,500,3.0F);

        //缩放动作
        ScaleToAction scaleTo=Actions.scaleTo(1.0F,1.0F,3.0F);

        //旋转动作
        RotateByAction rotateBy=Actions.rotateBy(360.0F,3.0F);

        //并行动作，包含 moveTo,scaleTo,rotateBy
        ParallelAction parallelAction=Actions.parallel(moveTo,scaleTo,rotateBy);

        //执行并行动作
        actor.addAction(parallelAction);
    }

    /**
     * 11、顺序动作（包含了延时动作的演示）：先延时，然后移动，再旋转并缩放
     */
    private void testSequenceAction(){
        //设置演员初始化状态
        actor.setPosition(0,0);
        actor.setScale(1.0F,1.0F);
        actor.setRotation(0);

        //缩放和旋转支点设置到演员中心
        actor.setOrigin(actor.getWidth()/2,actor.getHeight()/2);

        //1、延时动作，延时3秒
        DelayAction delay=Actions.delay(3.0F);

        //2、移动动作
        MoveToAction moveTo=Actions.moveTo(150,500,3.0F);

        //3、并行动作，包含 缩放 和 旋转 两个动作
        ParallelAction parallel=Actions.parallel(
                Actions.scaleTo(0.5F,0.5F,3.0F),
                Actions.rotateBy(360.0F,3.0F)
        );

        //顺序动作，包含delay,moveTo,parallel
        SequenceAction sequenceAction=Actions.sequence(delay,moveTo,parallel);

        //执行顺序动作
        actor.addAction(sequenceAction);

    }

    /**
     * 12、重复动作：重复 缩小，放大
     */
    private void testRepeatAction(){
        //设置演员显示到舞台中心
        actor.setPosition(actor.getStage().getWidth()/2-actor.getWidth()/2,
                actor.getStage().getHeight()/2-actor.getHeight()/2);

        //缩放和旋转支点设置到演员中心
        actor.setOrigin(actor.getWidth()/2,actor.getHeight()/2);

        //设置演员初始缩放比
        actor.setScale(1.0F,1.0F);

        //顺序动作：先缩小到0.5，再放大到1.0
        SequenceAction sequence=Actions.sequence(
                Actions.scaleTo(0.5F,0.5F,2.0F),
                Actions.scaleTo(1.0F,1.0F,2.0F)
        );

        //重复动作：重复执行sequence
        RepeatAction repeatAction=Actions.forever(sequence);

        //执行重复动作
        actor.addAction(repeatAction);
    }

    /**
     * 13.Runnable动作：适当时机执行自己的代码，与顺序动作一起使用可用于监听
     * 某个动作的完成
     */
    private void testRunnableAction(){
        //设置演员初始化状态
        actor.setPosition(0,0);

        //移动动作
        MoveToAction moveTo=Actions.moveTo(150,300,3.0F);

        //Runnable动作
        RunnableAction runnable=Actions.run(new Runnable() {
            @Override
            public void run() {
              //打印一句log表示动作已执行
                Gdx.app.log(TAG,"我在line395,动画播放完毕");
            }
        });
        //顺序动作：在moveTo动作执行完后执行runnable动作
        SequenceAction sequence=Actions.sequence(moveTo,runnable);

        //执行顺序动作
        actor.addAction(sequence);
    }

    /**
     * 14、After动作：可用于监听演员动作的执行完成
     */
    private void testAfterAction(){
        //设置演员初始化状态
        actor.setPosition(0,0);
        actor.setRotation(0);

        //缩放和旋转支点设置到演员中心
        actor.setOrigin(actor.getWidth()/2,actor.getHeight()/2);

        //移动动作，移动3秒
        MoveToAction moveTo=Actions.moveTo(150,300,3.0F);

        //旋转动作，旋转2秒
        RotateByAction rotateBy=Actions.rotateBy(360.0F,2.0F);

        //Runnable动作
        RunnableAction runnable=Actions.run(new Runnable() {
            @Override
            public void run() {
                Gdx.app.log(TAG,"我在Line427，演员的其他所有动作已经执行完了");
            }
        });

        //After动作，包含一个runnable动作
        AfterAction afterAction=Actions.after(runnable);

        //同时添加多个动作到演员：将立即执行moveTo和rotateBy,都执行完后执行afterAction
        actor.addAction(moveTo);
        actor.addAction(rotateBy);
        actor.addAction(afterAction);
    }




    @Override
    public void render() {
        //使用淡蓝色清屏
        Gdx.gl.glClearColor(0.75F,1,0.98F,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //更新舞台逻辑
        stage.act();
        //绘制舞台
        stage.draw();
    }

    @Override
    public void dispose() {
        //释放资源
        if (stage!=null){
            stage.dispose();
        }
        if (texture!=null){
            texture.dispose();
        }
    }
}
