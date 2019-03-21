package com.jakera.gdxtest.Box2dDemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by jakera on 2019/3/19.
 * 参考：https://blog.csdn.net/zqiang_55/article/details/53536265
 *       Libgdx扩展学习之Box2D_刚体的运动和贴图
 */

public class MoveBody extends ApplicationAdapter {
    World world;
    Box2DDebugRenderer box2DDebugRenderer;
    Body body;
    OrthographicCamera camera;

    float scene_width=12.8f;
    float scene_height=7.2f;

    Stage stage;
    SpriteBatch batch;
    Label forceLabel,impulseLabel,velocityLabel;
    Image image;
    BitmapFont font;

    @Override
    public void create() {
        world=new World(new Vector2(0.0f,-9.8f),true);
        box2DDebugRenderer=new Box2DDebugRenderer();

        image=new Image(new Texture("badlogic.jpg"));
        image.setSize(1.0f,1.0f);
//        image.setOrigin(image.getWidth()/2.0f,image.getHeight()/2.0f);

        BodyDef bodyDef=new BodyDef();
        bodyDef.type=BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(3,3);
        body=world.createBody(bodyDef);
        body.setUserData(image);

        PolygonShape polygonShape=new PolygonShape();
        polygonShape.setAsBox(0.5f,0.5f);

        FixtureDef fixtureDef=new FixtureDef();
        fixtureDef.shape=polygonShape;
        fixtureDef.density=1.0f;
        fixtureDef.restitution=1.0f;
        fixtureDef.friction=0.0f;
        body.createFixture(fixtureDef);
        polygonShape.dispose();

        createGround();

        camera=new OrthographicCamera(scene_width,scene_height);
        camera.position.set(scene_width/2,scene_height/2,0);
        camera.update();

        initStageUI();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    private void initStageUI(){
        stage=new Stage();
        batch=new SpriteBatch();
        font=new BitmapFont();

        Label.LabelStyle style=new Label.LabelStyle();
        style.font=font;
        style.font.getData().setScale(4);
        forceLabel=new Label("Force",style);
        forceLabel.setPosition(1200,800);
        impulseLabel=new Label("Impulse",style);
        impulseLabel.setPosition(1200,600);
        velocityLabel=new Label("LinearVelocity",style);
        velocityLabel.setPosition(1200,400);

        forceLabel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                forceLabel.setColor(Color.RED);
                impulseLabel.setColor(Color.WHITE);
                velocityLabel.setColor(Color.WHITE);
                //给物理施加100N的力
                body.applyForceToCenter(new Vector2(100f,0),true);
            }
        });

        impulseLabel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                impulseLabel.setColor(Color.RED);
                forceLabel.setColor(Color.WHITE);
                velocityLabel.setColor(Color.WHITE);
                body.applyLinearImpulse(new Vector2(0.8f,0),body.getWorldCenter(),true);
            }
        });

        velocityLabel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                velocityLabel.setColor(Color.RED);
                forceLabel.setColor(Color.WHITE);
                impulseLabel.setColor(Color.WHITE);
                //设置线性速度
                body.setLinearVelocity(new Vector2(1.2f,0));
            }
        });

        stage.addActor(forceLabel);
        stage.addActor(impulseLabel);
        stage.addActor(velocityLabel);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        world.step(1/60f,6,2);

        Gdx.gl.glClearColor(0.39f,0.58f,0.92f,1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        box2DDebugRenderer.render(world,camera.combined);

        batch.setProjectionMatrix(camera.combined);
        //Image的开始坐标和Body的坐标原点不一样，需要手动调整一下
        image.setPosition(body.getPosition().x-image.getWidth()*0.5f,
                body.getPosition().y-image.getHeight()*0.5f);

        batch.begin();
        image.draw(batch,1.0f);
        batch.end();

        stage.act();
        stage.draw();

        Gdx.app.log("MoveBody","Velocity.x="+body.getLinearVelocity().x);
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        font.dispose();
        world.dispose();
        box2DDebugRenderer.dispose();
    }

    private void createGround(){
        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(scene_width*0.5f,0.2f);
        Body body1=world.createBody(bodyDef);

        PolygonShape polygonShape=new PolygonShape();
        polygonShape.setAsBox(scene_width*0.5f,0.2f);
        body1.createFixture(polygonShape,0.0f);

        bodyDef.position.set(0.4f,scene_height*0.5f);
        Body body2=world.createBody(bodyDef);

        polygonShape.setAsBox(0.2f,scene_height*0.5f);;
        body2.createFixture(polygonShape,0);

        bodyDef.position.set(12.4f,scene_height*0.5f);
        Body body3=world.createBody(bodyDef);

        polygonShape.setAsBox(0.2f,scene_height*0.5f);
        body3.createFixture(polygonShape,0);
    }
}
