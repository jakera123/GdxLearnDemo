package com.jakera.gdxtest.ViewportDemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by jakera on 2019/3/7.
 * 手机屏幕多种多样，有着分辨率不同，屏幕宽高比也可能不同。
 * 同一张图片在不同手机中显示的位置和大小视觉上的感觉可能
 * 都不相同，为此我们需要对不同的手机屏幕进行适配，使相同
 * 的程序逻辑在不同的屏幕上显示的视觉效果一致。LibGDX 提
 * 供了一个方便的方法处理不同屏幕之间的适配问题，即使用视口（Viewport）。

 视口（Viewport）负责管理游戏相机并处理世界（代码/逻辑中
 所认为的内容显示边界）坐标与实际屏幕坐标之间的映射。视
 口字面上意思为眼睛视觉上能够看到的口（一般来说就是指手
 机屏幕的显示），在程序代码中指的是显示内容的逻辑世界边
 界（宽高），是给不同尺寸手机屏幕统一定义的一个固定的虚
 拟屏幕尺寸。视口通常结合舞台一起使用，例如给舞台设置一
 个宽高为 480*800 的伸展视口，则在代码/逻辑中舞台/屏幕
 宽高就可以当做是固定不变的 480*800，即舞台/屏幕左下角
 坐标为 (0, 0)，右上角坐标为 (480, 800)，最终绘制到不
 同尺寸的真正手机屏幕上时对 480*800 的绘制结果通过竖直
 和水平方向的适当缩放使之刚好显示到屏幕上，这样就达到
 了同样的一套程序逻辑在不同尺寸的屏幕上尽可能地保证了一致的显示效果。

 （1）ExtendViewport（延伸视口）： 视口保持和原屏幕相同的宽高比先适配其中一个方向（宽或高），然后另一个方向进行延伸。视口有可能延伸到屏幕外面导致部分内容不能显示。

 （2）ScreenViewport（屏幕视口）： 屏幕视口的世界尺寸基于原屏幕尺寸（宽高比相同）。默认 1 个世界单位 == 1 个屏幕像素，但这个比例关系可以通过 ScreenViewport 类中的方法（setUnitsPerPixel）进行修改。

 （3）ScalingViewport（缩放视口）： 缩放视口顾名思义就是将世界尺寸的水平或竖直方向进行相应的缩放（缩放比可能不同）以适配屏幕的宽高。创建缩放视口实例时需要指定缩放方式（枚举类型 Scaling）。ScalingViewport 主要有以下几种缩放方式：
 ---------------------
 作者：xietansheng
 来源：CSDN
 原文：https://blog.csdn.net/xietansheng/article/details/50187331
 版权声明：本文为博主原创文章，转载请附上博文链接！

 */

public class ViewportTestDemo extends ApplicationAdapter{
    private Texture texture;
    private Stage stage;
    private TestViewportActor actor;

    @Override
    public void create() {
        //创建纹理，badlogic.jpg图片的宽高为256*256
        texture=new Texture(Gdx.files.internal("badlogic.jpg"));
        //创建一个伸展视口，为了方便查看效果，世界的宽高设置为256*512（图片刚好在视口下半部分）
        Viewport strechViewport=new StretchViewport(256,512);
        //也可以使用下面方法创建伸展视口
//        Viewport stretchViewport=new ScalingViewport(Scaling.stretch,256,512);
        //使用指定的视口创建舞台，舞台的宽高为视口世界的宽高
        stage=new Stage(strechViewport);
        //创建演员
        actor=new TestViewportActor(new TextureRegion(texture));
        //添加演员到舞台
        stage.addActor(actor);
    }

    @Override
    public void render() {
        //红色清屏
        Gdx.gl.glClearColor(1,0,0,1);
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
