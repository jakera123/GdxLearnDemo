package com.jakera.gdxtest.ScreenDemo;

import com.badlogic.gdx.Game;

/**
 * Created by jakera on 2019/3/7.
 *
 * 游戏主程序的启动入口类，要使用场景需要将
 * MainGame改为继承Game抽象类
 *
 */

public class MainGame extends Game {

    //视口世界的宽高统一使用480*800，并统一使用伸展视口（StretchViewport）
    public static final float WORLD_WIDTH=400;
    public static final float WORLD_HEIGHT=800;

    //开始场景
    private StartScreen startScreen;

    //主游戏场景
    private GameScreen gameScreen;


    @Override
    public void create() {
        //创建开始场景
        startScreen=new StartScreen(this);

        //创建主游戏场景
        gameScreen=new GameScreen();

        //设置当前场景为开始场景
        setScreen(startScreen);
    }

    /**
     * 开始场景展示完毕后调用该方法切换到主游戏场景
     */
    public void showGameScreen(){
        //设置当前场景为主游戏场景
        setScreen(gameScreen);

        if (startScreen!=null){
            //由于startScreen，只有在游戏启动时展示一下，之后都不需要展示
            //所以启动完GameScreen后手动调用StartScreen的dispose()方法销毁开始场景
            startScreen.dispose();

            //场景销毁后，场景变量值空，防止二次调用dispose()方法
            startScreen=null;
        }
    }

    @Override
    public void dispose() {
        super.dispose();      //super.dispose()不能删除，在父类中还有其他操作（调用当前场景的hide方法）

        //游戏程序退出时，手动销毁还没有被销毁的场景
        if (startScreen!=null){
            startScreen.dispose();
            startScreen=null;
        }

        if (gameScreen!=null){
            gameScreen.dispose();
            gameScreen=null;
        }


    }
}
