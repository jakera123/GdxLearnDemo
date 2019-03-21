package com.jakera.gdxtest.WidgetDemo;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by jakera on 2019/3/11.
 */

public class CheckBoxDemo extends ApplicationAdapter {
    private static final String TAG=CheckBoxDemo.class.getSimpleName();

    /**
     * 复选框图片有点小，为了能明显看到显示效果，这里把视口世界的宽高设置为320*480，
     * 并统一使用伸展视口（StretchViewport）
     */
    public static final float WORLD_WIDTH=320;
    public static final float WORLD_HEIGHT=480;

    //舞台
    private Stage stage;

    //复选框选中状态的纹理
    private Texture checkboxOnTexture;

    //复选框未选中状态的纹理
    private Texture checkboxOffTexture;

    //位图字体
    private BitmapFont bitmapFont;

    //复选框
    private CheckBox checkBox;

    @Override
    public void create() {
        //设置日志输出级别
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        //使用伸展视口（StretchViewport）创建舞台
        stage=new Stage(new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT));

        //将输入处理设置到舞台（必输设置，否则点击没效果）
        Gdx.input.setInputProcessor(stage);

        /**
         * 第1步：创建复选框 选中 和 未选中 两种状态的纹理，以及创建位图字体（用于显示复选框文本）
         */
        checkboxOnTexture=new Texture(Gdx.files.internal("Button/checkbox_on.png"));
        checkboxOffTexture=new Texture(Gdx.files.internal("Button/checkbox_off.png"));

        /**
         * 创建位图字体：前面我们创建位图字体对象的时候手动指定了一个自己生成的字体文件，实际上
         * 创建位图字体对象时，如果没有指定字体文件（使用空参构造方法），BitmapFont构造方法中会
         * 默认指定一个字体文件，这个字体文件内嵌在gdx.jar包中，是一个包含了大小写字母，数字 和
         * 常用英文标点符号的字体文件。
         *
         * 查看源码可知BitmapFont空参构造方法如下所示：
         *  this(Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.fnt"),
         *  Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.png"),
         *  false,
         *  true);
         */
         bitmapFont=new BitmapFont();

        /**
         * 第2步：创建CheckBoxStyle
         */
        CheckBox.CheckBoxStyle style=new CheckBox.CheckBoxStyle();

        //设置style的 选中 和 未选中 状态的纹理区域
        style.checkboxOn=new TextureRegionDrawable(new TextureRegion(checkboxOnTexture));
        style.checkboxOff=new TextureRegionDrawable(new TextureRegion(checkboxOffTexture));

        //设置复选框文本的位图字体
        style.font=bitmapFont;

        //也可以设置复选框文本的字体颜色（RGBA）
        style.fontColor=new Color(1,0,0,1);

        /**
         * 第3步：创建CheckBox
         */
        checkBox=new CheckBox("Hello",style);

        //可以手动设置复选框的选中/未选中状态
//        checkBox.setChecked(true);

        //设置复选框的（选中/未选中）状态改变监听器
        checkBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                Gdx.app.log(TAG,"复选框是否被选中："+checkBox.isChecked());
            }
        });
        /**
         * 第4步：添加checkBox到舞台
         */
        stage.addActor(checkBox);
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
        //应用退出时释放资源
        if (checkboxOnTexture!=null){
            checkboxOnTexture.dispose();
        }
        if (checkboxOffTexture!=null){
            checkboxOffTexture.dispose();
        }
        if (bitmapFont!=null){
            bitmapFont.dispose();
        }
        if (stage!=null){
            stage.dispose();
        }

    }
}
