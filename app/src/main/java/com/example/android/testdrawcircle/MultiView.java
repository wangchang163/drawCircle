package com.example.android.testdrawcircle;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MultiView extends View {

    private Paint mPaint;
    private int mWidth,mHeight;
    private float mRadius=150f;
    private int mTmpPointCount=3;
    private ValueAnimator mValueAnimator;
    public MultiView(Context context) {
        super(context);
        init();
    }

    public MultiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MultiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint=new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w>0&&h>0){
            mHeight=h;
            mWidth=w;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        //计算每个点之间的间隔角度
        double angle = 2.0 * Math.PI / mTmpPointCount;
        //移动到路径起点
        path.moveTo(
                mWidth / 2 + (float) (mRadius * Math.cos(0.0)),
                mHeight / 2 + (float) (mRadius * Math.sin(0.0)));
        //依次连接多边形顶点
        for (int i = 1; i < mTmpPointCount + 1; i++) {
            path.lineTo(
                    mWidth / 2 + (float) (mRadius * Math.cos(angle * i)),
                    mHeight / 2 + (float) (mRadius * Math.sin(angle * i)));
        }
        //闭合Path
        path.close();
        canvas.drawPath(path,mPaint);
    }
    public void startAnimation(int count) {
        mValueAnimator = ValueAnimator.ofInt(3, count);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mTmpPointCount = (int) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        mValueAnimator.setDuration(6000);
        mValueAnimator.setRepeatCount(-1);
        mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mValueAnimator.start();
    }
}
