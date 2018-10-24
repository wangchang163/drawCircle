package com.example.android.testdrawcircle;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PaintCircleView extends View {
    /**
     * 画笔，用于View内部内容的绘制
     */
    private Paint mPaint;

    /**
     * 用于存储View的宽高
     */
    private int mWidth, mHeight;

    /**
     * 圆环宽度
     * @param context
     */
    private int mInnerRadius=30;

    private ValueAnimator valueAnimator;

    /**
     * 绘制的外环总个数
     */
    private int mOutterRingCount = 4;

    /**
     * 存储计算所得的外环半径
     */
    private int[] mRadius = new int[5];
    /* 计算外环半径 */
    private void calculateRadius() {
        for (int i = 0; i < 5; i++) {
            mRadius[i] = mInnerRadius + (i * 2 + 1) * mInnerRadius / 2;
        }
    }


    public PaintCircleView(Context context) {
        super(context);
        init();
    }

    public PaintCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaintCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0){
            mWidth = w;
            mHeight = h;
        }
    }

    private void init(){
        //新建画笔对象
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //为画笔设置颜色
        mPaint.setColor(Color.BLUE);
        //设置画笔实心空心,Style.FILL--实心,Style.STROKE--空心
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 前两个参数分别代表圆心的X坐标和Y坐标
         * 第三个参数是圆半径
         * 第四个参数是绘制圆所使用的画笔
         */
        calculateRadius();
        canvas.drawCircle(mWidth / 2, mHeight / 2, mInnerRadius, mPaint);
        for (int i = 0; i < mOutterRingCount; i++) {
            mPaint.setAlpha(255 - (int) (255 * ((float) (i + 1) / 5)));
            canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius[i], mPaint);
        }

    }

    public void startAnimation(){
        valueAnimator=ValueAnimator.ofInt(0,5);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOutterRingCount= (int) animation.getAnimatedValue();
                Log.e("mOutterRingCount",mOutterRingCount+"");
                postInvalidate();
            }
        });
        valueAnimator.start();
    }
}
