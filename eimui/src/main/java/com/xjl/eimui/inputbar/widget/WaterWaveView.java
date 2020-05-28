package com.xjl.eimui.inputbar.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

public class WaterWaveView extends View {

    private static final String TAG = "WaterWaveView";

    /**
     * 数值参数类型
     */
    //水波纹的数量
    private final int WAVE_COUNT = 5;
    //整个控件的宽高
    private int width, height;
    //圆心
    private int xCenter, yCenter;
    //最大半径
    private float radius;
    //最小半径
    private int unitR;
    //递增的圆半径长度
    private Integer rStep[] = new Integer[WAVE_COUNT];
    /**
     * 颜色类型参数
     */
    //同心圆颜色
    private int circleColor;
    /**
     * 工具类参数
     */
    private Paint paint;
    /**
     * 播放类参数
     */
    //代表一秒25帧
    private int frame = 25;
    //帧间隙
    private int interval = 1000 / frame;
    //周期即一个圆从内测到外侧的运动时间
    private int until = 2;
    //每帧运动
    private int distance;

    private boolean isRuning = false;

    private boolean isShowCenterCircle = true;

    private int startAlaph = 255;

    public WaterWaveView(Context context) {
        super(context);
        init();
    }

    public WaterWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //仅初始化画笔 未设置画笔颜色
    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    public void setColor(String color) {
        this.circleColor = Color.parseColor(color);
        paint.setColor(circleColor);
        if (!isRuning) {
            invalidate();
        }
    }

    public void setShowCenterCircle(boolean b) {
        this.isShowCenterCircle = b;
    }

    public void setStartAlaph(int startAlaph) {
        if (startAlaph > 255)
            this.startAlaph = 255;
        if (startAlaph < 0)
            this.startAlaph = 0;
        else
            this.startAlaph = startAlaph;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        xCenter = width / 2;
        yCenter = height / 2;
        radius = width / 2.0f;
        //最小圆的半径
        unitR = width / WAVE_COUNT / 2;
        for (int i = 0; i < WAVE_COUNT; i++) {
            rStep[i] = unitR * (i + 1);
        }
        distance = (int) (radius / frame / until);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.UNSPECIFIED || widthMode == MeasureSpec.AT_MOST) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(dp2Px(240), MeasureSpec.EXACTLY);
        }

        if (heightMode == MeasureSpec.UNSPECIFIED || heightMode == MeasureSpec.AT_MOST) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(dp2Px(240), MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (circleColor != 0) {
            drawWave(canvas);
            if (isShowCenterCircle) {
                drawCenterCircle(canvas);
            }
        }
    }

    private void drawWave(Canvas canvas) {
        for (int i = 0; i < WAVE_COUNT; i++) {
            paint.setAlpha((int) (startAlaph * (1.1 - rStep[i] / radius)));
            canvas.drawCircle(xCenter, yCenter, rStep[i], paint);
        }

        for (int i = 0; i < rStep.length; i++) {
            //逐渐扩大 半径向前推进
            rStep[i] = rStep[i] + distance;
            if (rStep[i] > radius) {
                rStep[i] = unitR;
            }
        }

        if (isRuning) {
            postInvalidateDelayed(interval);
        }
    }

    private void drawCenterCircle(Canvas canvas) {
        paint.setAlpha(255);
        canvas.drawCircle(xCenter, yCenter, unitR * 2, paint);
    }


    public void start() {
        if (!isRuning) {
            invalidate();
            isRuning = true;
        }
    }

    public void stop() {
        isRuning = false;
    }

    private int dp2Px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

}
