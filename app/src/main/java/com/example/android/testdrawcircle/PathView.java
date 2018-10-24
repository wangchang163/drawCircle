package com.example.android.testdrawcircle;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PathView extends View {
    private Paint mPaint;
    private int mWidth, mHeight;
    private int mRadius = 200;
    private float x = 0f;
    private Path mPath3;
    private ValueAnimator mValueAnimator;

    public PathView(Context context) {
        super(context);
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //清空上一次Path中存放的所有路径
        mPaint.setColor(Color.RED);
        mPath3.reset();
        //移动路径起点到B点
        mPath3.moveTo(mWidth / 2 - mRadius, mHeight / 2);
        //绘制四分之一圆弧BA
        mPath3.addArc(new RectF(mWidth / 2 - mRadius, mHeight / 2 - mRadius, mWidth / 2 + mRadius,
                mHeight / 2 + mRadius), 180, 90);
        //绘制直线AP
        mPath3.lineTo(mWidth / 2 - x, mHeight / 2 - x);
        //闭合曲线，默认绘制直线PB
        mPath3.close();
        canvas.drawPath(mPath3, mPaint);

        //清空上一次Path中存放的所有路径
        mPaint.setColor(Color.CYAN);
        mPath3.reset();
        //移动路径起点到B点
        mPath3.moveTo(mWidth / 2 - mRadius, mHeight / 2);
        //绘制四分之一圆弧BA
        mPath3.addArc(new RectF(mWidth / 2 - mRadius, mHeight / 2 - mRadius, mWidth / 2 + mRadius,
                mHeight / 2 + mRadius), 90, 90);
        //绘制直线AP
        mPath3.lineTo(mWidth / 2 - x, mHeight / 2 + x);
        //闭合曲线，默认绘制直线PB
        mPath3.close();
        canvas.drawPath(mPath3, mPaint);

        //清空上一次Path中存放的所有路径
        mPaint.setColor(Color.BLUE);
        mPath3.reset();
        //移动路径起点到B点
        mPath3.moveTo(mWidth / 2 - mRadius, mHeight / 2);
        //绘制四分之一圆弧BA
        mPath3.addArc(new RectF(mWidth / 2 - mRadius, mHeight / 2 - mRadius, mWidth / 2 + mRadius,
                mHeight / 2 + mRadius), 0, 90);
        //绘制直线AP
        mPath3.lineTo(mWidth / 2 + x, mHeight / 2 + x);
        //闭合曲线，默认绘制直线PB
        mPath3.close();
        canvas.drawPath(mPath3, mPaint);

        //清空上一次Path中存放的所有路径
        mPaint.setColor(Color.GREEN);
        mPath3.reset();
        //移动路径起点到B点
        mPath3.moveTo(mWidth / 2 - mRadius, mHeight / 2);
        //绘制四分之一圆弧BA
        mPath3.addArc(new RectF(mWidth / 2 - mRadius, mHeight / 2 - mRadius, mWidth / 2 + mRadius,
                mHeight / 2 + mRadius), 270, 90);
        //绘制直线AP
        mPath3.lineTo(mWidth / 2 + x, mHeight / 2 - x);
        //闭合曲线，默认绘制直线PB
        mPath3.close();
        canvas.drawPath(mPath3, mPaint);

    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.YELLOW);
        mPath3 = new Path();

    }

    public void startAnimation() {
        //新建ValueAnimator对象
        mValueAnimator = ValueAnimator.ofFloat(0f, mRadius / 2f);
        //设置动画单次时长
        mValueAnimator.setDuration(3000);
        //设置动画重复模式，REVERSE--反转，RESTART--重新开始
        mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //设置动画重复次数，-1 --- INFINE
        mValueAnimator.setRepeatCount(-1);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //更新P点坐标
                x = (float) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        mValueAnimator.start();
    }
}
