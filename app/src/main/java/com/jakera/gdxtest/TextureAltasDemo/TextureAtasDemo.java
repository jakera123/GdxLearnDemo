package com.jakera.gdxtest.TextureAltasDemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by jakera on 2019/3/7.
 *
 * 源自：https://blog.csdn.net/xietansheng/article/details/50187651
 */

public class TextureAtasDemo extends ApplicationAdapter {
    private SpriteBatch batch;

    //纹理图集
    private TextureAtlas atlas;

    //每个小图在纹理图集中对应的名称常量（小图的原文件名，定义在Myatals.atlas文件中）
    public static final String MAN="man";             //人
    public static final String MUSHROOM="mushroom";   //蘑菇
    public static final String FLOWER="flower";       //鲜花
    public static final String BUTTON="button";       //按钮

    //小图对应的纹理区域或精灵
    private TextureRegion manRegion;                   //人
    private Sprite mushroomSprite;                     //蘑菇
    private Sprite flowerSprite;                       //鲜花
    private TextureRegion button1Region;               //按钮1
    private TextureRegion button2Region;               //按钮2

    @Override
    public void create() {
        //创建SpriteBatch
        batch=new SpriteBatch();

        //读取myatlas.atlas文件，创建纹理图集
        atlas=new TextureAtlas(Gdx.files.internal("atlas/myatlas.atlas"));

        //根据名称从纹理图集中获取纹理区域
        manRegion=atlas.findRegion(MAN);

        //也可以根据名称直接由纹理图集创建精灵
        mushroomSprite=atlas.createSprite(MUSHROOM);
        flowerSprite=atlas.createSprite(FLOWER);

        /**
         * 根据 名称 和 索引 从文理图集中获取纹理区域
         *
         * 特别说明：
         *     纹理图集中的小图名称一般就是小图的原文件名，但可以给小图
         *加上一个索引（index）属性，就是在文件后面加上下划线和表示索引
         * 的数字（name_index.png）,例如Button_1.png和Button_2.png这两
         * 个小图，gdx-tools在合成纹理图集时会使用相同的名称（Button）分
         * 别加上对应的索引值（1和2）表示，这样有助业务相同的图片进行统一
         * 命名。纹理图集对小图的描述定义在myatlas.atlas文件中，这是一个
         * 文本文件，可以用记事本打开查看
         *
         */
        button1Region=atlas.findRegion(BUTTON,1);
        button2Region=atlas.findRegion(BUTTON,2);

        flowerSprite.setPosition(190,80);
        mushroomSprite.setPosition(190,290);
    }

    @Override
    public void render() {
        //黑色清屏
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //开始绘制
        batch.begin();

        //绘制精灵
        mushroomSprite.draw(batch);   //蘑菇
        flowerSprite.draw(batch);     //鲜花

        //绘制纹理区域
        batch.draw(manRegion,30,70);         //人
        batch.draw(button1Region,30,340);
        batch.draw(button2Region,30,286);

        //绘制结事
        batch.end();

    }

    @Override
    public void dispose() {
        //当应用退出时释放资源
        if (batch!=null){
            batch.dispose();
        }
        //纹理图集关联了一张合成的大图，应用退出时需要释放纹理图集资源
        if (atlas!=null){
            atlas.dispose();
        }

    }
}
