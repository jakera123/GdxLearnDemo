package com.jakera.gdxtest.Others;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by jakera on 2019/3/14.
 * 网络访问测试demo
 */

public class NetAccessDemo extends ApplicationAdapter {
    private static final String TAG=NetAccessDemo.class.getSimpleName();
    //视口世界的宽高统使用480*800，并统一使用伸展视口（StretchViewPort）
    public static final float WORLD_WIDTH=480;
    public static final float WORLD_HEIGHT=800;

    //舞台
    private Stage stage;

    //纹理
    private Texture texture;

    private Image actor;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        //使用伸展视口（StretchViewPort）创建舞台
        stage=new Stage(new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT));

        //从网络中加载一张图片
        loadImageFromNet();
    }
    /**
     * 从网络中加载一张图片
     * 不一定能访问成功！
     */
    private void loadImageFromNet(){
        String url="http://pic3.16pic.com/00/10/33/16pic_1033226_b.jpg";

        //1、创建请求构建器
        HttpRequestBuilder requestBuilder=new HttpRequestBuilder();

        //2、构建请求对象
        Net.HttpRequest httpRequest=requestBuilder.newRequest().method(Net.HttpMethods.GET).url(url).build();

        //3、发送请求，监听结果回调
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                //获取响应状态
                HttpStatus httpStatus=httpResponse.getStatus();
                if (httpStatus.getStatusCode()==200){
                    //请求成功
                    Gdx.app.log(TAG,"请求成功");

                    //以字节数组的方式获取响应内容
                    final byte[] result=httpResponse.getResult();

                    //还可以以流或字符串的方式获取
//                    httpResponse.getResultAsStream();
//                    httpResponse.getResultAsString();

                    /**
                     * 在响应回调中属于其他线程，获取到响应结果后需要
                     * 提交到 渲染线程（Create 的render 方法执行所在线程）处理
                     */
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            //反字节数组加载为Pixmap
                            Pixmap pixmap=new Pixmap(result,0,result.length);

                            //把pixmap加载为纹理
                            texture=new Texture(pixmap);

                            //pixmap 不再需要使用到，释放内存占用
                            pixmap.dispose();

                            //使用纹理创建演员
                            actor=new Image(texture);

                            //添加演员到舞台
                            stage.addActor(actor);
                        }
                    });
                }else {
                    Gdx.app.error(TAG,"请求失败，状态码："+httpStatus.getStatusCode());
                }
            }

            @Override
            public void failed(Throwable throwable) {
                    Gdx.app.error(TAG,"请求失败",throwable);
            }

            @Override
            public void cancelled() {
                Gdx.app.log(TAG,"请求被取消");
            }
        });
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
        //当应用退出时释放资源
        if (stage!=null){
            stage.dispose();
        }
        if (texture!=null){
            texture.dispose();
        }
    }
}
