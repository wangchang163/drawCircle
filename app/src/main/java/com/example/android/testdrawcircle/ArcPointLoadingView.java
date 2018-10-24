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

public class ArcPointLoadingView extends View {

    private Paint mPaint;
    private int mWidth,mHeight;
    private float mRadius=150f;
    private ValueAnimator mValueAnimator;
    private Path path;
    private float x=20f;
    private float mSmallRadius=15f;

    public ArcPointLoadingView(Context context) {
        super(context);
        init();
    }

    public ArcPointLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ArcPointLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
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
        path=new Path();
        path.reset();
        path.moveTo(mWidth/2,mHeight/2);
        path.lineTo(mWidth/2+(float)(mRadius*Math.cos(x)),mHeight/2-(float)(mRadius*Math.sin(x)));
        path.addArc(new RectF(mWidth/2-mRadius,mHeight/2-mRadius,mWidth/2+mRadius,mHeight/2+mRadius),-x,-360+2*x);
        path.lineTo(mWidth/2,mHeight/2);
        path.close();
        canvas.drawPath(path,mPaint);

        canvas.drawCircle(mWidth-mSmallRadius-(mWidth/2-mSmallRadius-x/20f*(mWidth/2-mSmallRadius)),mHeight/2,mSmallRadius,mPaint);


    }

    private void init() {
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
    }

    public void startAnimation() {
        mValueAnimator = ValueAnimator.ofFloat(20f, 0f);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                x = (float) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        mValueAnimator.setDuration(2000);
        mValueAnimator.setRepeatCount(-1);
        mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mValueAnimator.start();
    }
}
