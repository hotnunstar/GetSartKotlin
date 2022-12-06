package ipca.budget.customview

import android.content.ComponentCallbacks
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class VerticalSlider : View {

    private var touchY = 0f
    private var _percentage = 0f
    var percentage : Float
        get() {
            return _percentage
        }
    set(value) {
        _percentage = value
        touchY = height * (1 - _percentage / 100)
        invalidate()
    }

    private var onValueChange : ((Int) -> Unit)? = null
    fun setOnValueChange(callback: (Int) -> Unit) {
        onValueChange = callback
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        val paint = Paint()

        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL
        val innerRect = Rect(0, touchY.toInt(), width, height)
        canvas?.drawRect(innerRect, paint)


        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10F
        val rect = Rect(0, 0, width, height)
        canvas?.drawRect(rect, paint)

        paint.color = Color.YELLOW
        paint.style = Paint.Style.FILL
        paint.textSize = 60F
        canvas?.drawText("${_percentage.toInt()}%",30f, height-70f, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)
        val y = event?.y

        when(event?.action) {
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_MOVE -> {
                y?.let {
                    touchY = it
                    if (touchY <= 0) touchY = 0F
                    if (touchY >= height) touchY = height.toFloat()
                    _percentage = ((height - touchY) / height) * 100

                    onValueChange?.invoke(_percentage.toInt())
                    invalidate()
                }
                return true
            }
        }
        return false
    }
}