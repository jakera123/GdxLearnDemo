package com.jakera.gdxtest.CameraDemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by jakera on 2019/3/21.
 */

public class OrthographicCameraController extends ApplicationAdapter {
    private final static String TAG=OrthographicCameraController.class.getSimpleName();
    static final int WIDTH=10;
    static final int HEITHT=10;

    private OrthographicCamera cam;   //控制观察
    private SpriteBatch batch;        //渲染出这个world

    private Sprite mapSprite;         //绘制地图
    private float rotationSpeed;

    @Override
    public void create() {
        rotationSpeed=0.5f;

        mapSprite=new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
        mapSprite.setPosition(0,0);
        mapSprite.setSize(WIDTH,HEITHT);  //100*100Unit 这里不是像素

        float w=Gdx.graphics.getWidth();
        float h=Gdx.graphics.getHeight();

        //使用所给的宽高乘以相对应的比例构建一个新的正交相机
        cam=new OrthographicCamera(30,30*(h/w));
        //视窗的宽和高决定我们可以看到的整个世界的多在部分，并且根据设备宽高比例做了调整
        cam.position.set(WIDTH/2,HEITHT/2,0);
        cam.update();    //最重要的一步，更新摄像头，才能让前面的设置生效

        batch=new SpriteBatch();
    }

    @Override
    public void render() {
        handleInput();
        cam.update();
        //将精灵和摄像头关联（view 和 投影矩阵）
        batch.setProjectionMatrix(cam.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        mapSprite.draw(batch);
        batch.end();
    }

    //由于Android的键盘监听没有成功
    //我这里采用软键盘弹出 Gdx.input.setOnscreenKeyboardVisible(true);
    //仍然无法正常获取按键的监听
    //但是isTouched方法可以正常监听，因此在此处测试各用例！
    private void handleInput(){
        if (Gdx.input.isTouched()){
            cam.rotate(rotationSpeed,0,0,1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            cam.zoom+=0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)){
            cam.zoom-=0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (cam.position.x>0)
                cam.translate(-3,0,0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if (cam.position.x<1024)
                cam.translate(0,-3,0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            if (cam.position.y>0)
                cam.translate(0,-3,0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            if (cam.position.y<1024)
                cam.translate(0,3,0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            cam.rotate(-rotationSpeed,0,0,1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)){
            cam.rotate(rotationSpeed,0,0,1);
        }
    }

    //改变大小的时候，不同的分辨率和长宽比的处理策略
    @Override
    public void resize(int width, int height) {
        cam.viewportWidth=30f;   //固定的30unit
        cam.viewportHeight=30f*height/width;
        cam.update();
    }
}
