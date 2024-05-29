package com.xjl.eimui.inputbar.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

class WaterWaveView : View {
    private val TAG = "WaterWaveView"

    /**
     * 数值参数类型
     */
    //水波纹的数量
    private val WAVE_COUNT = 5

    //整个控件的宽高
    private var width = 0
    private var height = 0

    //圆心
    private var xCenter = 0
    private var yCenter = 0

    //最大半径
    private var radius = 0f

    //最小半径
    private var unitR = 0

    //递增的圆半径长度
    private val rStep = arrayOfNulls<Int>(WAVE_COUNT)

    /**
     * 颜色类型参数
     */
    //同心圆颜色
    private var circleColor = 0

    /**
     * 工具类参数
     */
    private lateinit var paint: Paint

    /**
     * 播放类参数
     */
    //代表一秒25帧
    private val frame = 25

    //帧间隙
    private val interval = 1000 / frame

    //周期即一个圆从内测到外侧的运动时间
    private val until = 2

    //每帧运动
    private var distance = 0
    private var isRuning = false
    private var isShowCenterCircle = true
    private var startAlaph = 255

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    //仅初始化画笔 未设置画笔颜色
    private fun init() {
        paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
    }

    fun setColor(color: String?) {
        circleColor = Color.parseColor(color)
        paint.color = circleColor
        if (!isRuning) {
            invalidate()
        }
    }

    fun setShowCenterCircle(b: Boolean) {
        isShowCenterCircle = b
    }

    fun setStartAlaph(startAlaph: Int) {
        if (startAlaph > 255) this.startAlaph = 255
        if (startAlaph < 0) this.startAlaph = 0 else this.startAlaph = startAlaph
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w
        height = h
        xCenter = width / 2
        yCenter = height / 2
        radius = width / 2.0f
        //最小圆的半径
        unitR = width / WAVE_COUNT / 2
        for (i in 0 until WAVE_COUNT) {
            rStep[i] = unitR * (i + 1)
        }
        distance = (radius / frame / until).toInt()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthMeasureSpec = widthMeasureSpec
        var heightMeasureSpec = heightMeasureSpec
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        if (widthMode == MeasureSpec.UNSPECIFIED || widthMode == MeasureSpec.AT_MOST) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(dp2Px(240), MeasureSpec.EXACTLY)
        }
        if (heightMode == MeasureSpec.UNSPECIFIED || heightMode == MeasureSpec.AT_MOST) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(dp2Px(240), MeasureSpec.EXACTLY)
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (circleColor != 0) {
            drawWave(canvas)
            if (isShowCenterCircle) {
                drawCenterCircle(canvas)
            }
        }
    }

    private fun drawWave(canvas: Canvas) {
        for (i in 0 until WAVE_COUNT) {
            paint.alpha = (startAlaph * (1.1 - rStep[i]!! / radius)).toInt()
            canvas.drawCircle(xCenter.toFloat(), yCenter.toFloat(), rStep[i]!!.toFloat(), paint)
        }
        for (i in rStep.indices) {
            //逐渐扩大 半径向前推进
            rStep[i] = rStep[i]!! + distance
            if (rStep[i]!! > radius) {
                rStep[i] = unitR
            }
        }
        if (isRuning) {
            postInvalidateDelayed(interval.toLong())
        }
    }

    private fun drawCenterCircle(canvas: Canvas) {
        paint.alpha = 255
        canvas.drawCircle(xCenter.toFloat(), yCenter.toFloat(), (unitR * 2).toFloat(), paint)
    }

    fun start() {
        if (!isRuning) {
            invalidate()
            isRuning = true
        }
    }

    fun stop() {
        isRuning = false
    }

    private fun dp2Px(dpValue: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpValue.toFloat(),
            resources.displayMetrics
        ).toInt()
    }


}
