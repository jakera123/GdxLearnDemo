package com.jakera.gdxtest.ActionDemo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by jakera on 2019/3/8.
 * 自定义演员（绘制时处理 位置，尺寸，缩放比，旋转角度和color/alpha等属性）
 */

public class TestActionActor extends Actor {
    private TextureRegion region;
    public TestActionActor(TextureRegion region){
        super();
        this.region=region;
        setSize(this.region.getRegionWidth(),this.region.getRegionHeight());;
    }
    public TextureRegion getRegion(){
        return region;
    }
    public void setRegion(TextureRegion region){
        this.region=region;
        setSize(this.region.getRegionWidth(),this.region.getRegionHeight());;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (region==null||!isVisible()){
            return;
        }
        /**
         * 先把batch原本的color保存起来，因为batch是从外部传进来的，最好不要改
         * 变它原本的状态，但在这里需要重新设置batch的color，所以先保存起来，
         * 等当前方法执行完时再将batch原来的color设置回去。
         */
        Color tempBatchColor=batch.getColor();
        /**
         * 实际上演员并没有单独的alpha属性,alpha包含在颜色（Color）属性中，rgba color
         * 中的a表示alpha;演员有alpha值，而父节点（舞台/演员组）中也有alpha值（parentAlpha）,
         * 由于最终在演员节点中才真正把纹理绘制在屏幕上，才是真正绘制的地方，而父节点一般用
         * 于组织演员，不会直接绘制任何纹理，透明度alpha值只有在绘制时才能体现出来，所以
         * 父节点无法体现自己的alpha值，因此父节点会将自己的alpha值（就是draw方法中的参数parentAlpha）
         * 传递给它自己的所有子节点，即最终直接绘制纹理的演员，让演员结合自身的alpha值在绘制时综合体现
         */
        //获取演员的color属性
        Color color=getColor();

        /**
         * 处理color/alpha属性，即将演员的rgba color设置到纹理画布batch.
         * 其中的alpha需要结合演员和父节点的alpha，即演员的alpha与父节点
         * 的alpha相乘，例如：父节点的alpha为0.5，演员的alpha为0.5，那么最
         * 终的显示效果就是0.5*0.5=0.25
         */
        batch.setColor(color.r,color.g,color.b,color.a*parentAlpha);

        //处理 位置，缩放和旋转的支点，尺寸，缩放比，旋转角度
        batch.draw(
                region,
                getX(),getY(),
                getOriginX(),getOriginY(),
                getWidth(),getHeight(),
                getScaleX(),getScaleY(),
                getRotation()
        );

        //将batch原本的color设置回去
        batch.setColor(tempBatchColor);
    }
}
