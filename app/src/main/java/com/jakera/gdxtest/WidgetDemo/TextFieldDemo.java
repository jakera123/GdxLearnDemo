package com.jakera.gdxtest.WidgetDemo;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by jakera on 2019/3/12.
 * 文本框主要用于提供给用户输入文字，获取用户输出，例如在登录时让用户输入用户名和密码等。文本框一般由背景图片（文本框的边框）和 光标（也用图片来表示）组成。为了方便演示，这里不再引入外部的图片和字体文件，而是使用 Pixmap 自己在内存中创建图片生成纹理，并且直接使用 gdx.jar 包中的字体文件（字体较小，可能会显示不清晰）。
 ---------------------
 作者：xietansheng
 来源：CSDN
 原文：https://blog.csdn.net/xietansheng/article/details/50187911
 版权声明：本文为博主原创文章，转载请附上博文链接！
 */

public class TextFieldDemo extends ApplicationAdapter {
    private static final String TAG=TextFieldDemo.class.getSimpleName();

    //视口世界的宽高统使用480*800，并统一使用伸展视口（StretchViewport）
    public static final float WORLD_WIDTH=480;
    public static final float WORLD_HEIGHT=800;

    //文本框的宽高
    public static final int TEXT_FIELD_WIDTH=300;
    public static final int TEXT_FIELD_HEIGHT=50;

    //舞台
    private Stage stage;

    //文本框背景纹理
    private Texture bgTexture;

    //文本框中的光标纹理
    private Texture cursorTexture;

    //位图字体
    private BitmapFont bitmapFont;

    //文本框（用户名）
    private TextField usernameTextField;

    //文本框（密码）
    private TextField passwordTextField;

    @Override
    public void create() {
        //设置日志输出级别
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        //使用伸展视口（StretchViewport）创建舞台
        stage=new Stage(new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT));
        //将输入处理设置到舞台（必须设置，否则文本框无法获取焦点）
        Gdx.input.setInputProcessor(stage);
        /**
         * 第一步：创建文本框背景纹理，光标纹理，以及创建位图字体（用于显示文本框中的文本）
         *
         * 为了方便演示，这里创建纹理不再用图片来创建，而是使用Pixmap来创建
         */
        bgTexture=createBackgroudTexture();
        cursorTexture=createCursorTexture();

        //为了方便演示，这里直接使用gdx.jar中自带的字体文件创建位图字体（只要在BitmapFont中包
        // 含有的字符才能够被输入）
        bitmapFont=new BitmapFont();

        //gdx.jar 中自带的字体比较小，这里放大一下
        bitmapFont.getData().setScale(2.0F);

        /**
         * 第二步：创建TextFieldStyle
         */
        TextField.TextFieldStyle style=new TextField.TextFieldStyle();

        //设置背景纹理区域
        style.background=new TextureRegionDrawable(new TextureRegion(bgTexture));

        //设置光标纹理区域
        style.cursor=new TextureRegionDrawable(new TextureRegion(cursorTexture));

        //设置文本框显示文本的字体来源
        style.font=bitmapFont;

        //设置文本框字体颜色为白色
        style.fontColor=new Color(1,1,1,1);

        /**
         * 第三步：创建TextField
         */
        usernameTextField=new TextField("",style);
        passwordTextField=new TextField("",style);

        //设置文本框的位置
        usernameTextField.setPosition(90,500);
        passwordTextField.setPosition(90,430);

        //文本框中的文字居中对齐
        usernameTextField.setAlignment(Align.center);
        passwordTextField.setAlignment(Align.center);

        //用于显示密码的文本框，需要将文本框设置为密码模式
        passwordTextField.setPasswordMode(true);
        //显示密码时用*号代替密码字符
        passwordTextField.setPasswordCharacter('*');

        /**
         * 第4步：添加TextField到舞台
         */
        stage.addActor(usernameTextField);
        stage.addActor(passwordTextField);

    }
    /**
     * 创建文本框的背景纹理
     */
    private Texture createBackgroudTexture(){
        Pixmap pixmap=new Pixmap(TEXT_FIELD_HEIGHT,TEXT_FIELD_HEIGHT,Pixmap.Format.RGBA8888);
        pixmap.setColor(1,0,0,1);
        pixmap.drawRectangle(0,0,pixmap.getWidth(),pixmap.getHeight());
        Texture texture=new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }


    /**
     * 创建文本框中的光标纹理
     */
    private Texture createCursorTexture(){
        Pixmap pixmap=new Pixmap(1,TEXT_FIELD_HEIGHT-4,Pixmap.Format.RGBA8888);
        pixmap.setColor(1,0,0,1);
        pixmap.fill();
        Texture texture=new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    @Override
    public void render() {
        /**
         * 当按下 回车键 时获取文本框中的用户名和密码 输出Log
         */
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            Gdx.app.log(TAG,"username="+usernameTextField.getText());
            Gdx.app.log(TAG,"password="+passwordTextField.getText());
        }

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
        if (bgTexture!=null){
            bgTexture.dispose();
        }
        if (cursorTexture!=null){
            cursorTexture.dispose();
        }
        if (bitmapFont!=null){
            bitmapFont.dispose();
        }
        if (stage!=null){
            stage.dispose();
        }
    }
}
