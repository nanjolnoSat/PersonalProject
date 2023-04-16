package com.mishaki.tableview

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.os.Looper
import android.util.TypedValue
import androidx.recyclerview.widget.RecyclerView

class TabScrollBar(private val recyclerView: RecyclerView) : RecyclerView.ItemDecoration(), ScrollManager.HorizontalScrollBar {
    private var barWidth = 150f
    private var barHeight = 10f
    private var barMarginHorizontal = 20f
    private var barMarginBottom = 20f
    private var barMarginFirstColumn = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, recyclerView.context.resources.displayMetrics)

    private var scrollPosition = 0f
    private var isShowing = false
    private var barShowingTime = 500L

    private val rect = RectF()
    private val paint = Paint().also {
        it.isAntiAlias = true
        it.style = Paint.Style.FILL
        it.color = 0xffcccccc.toInt()
    }

    private val handler = android.os.Handler(Looper.getMainLooper())
    private val dismissAction = Runnable {
        isShowing = false
        recyclerView.postInvalidateOnAnimation()
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        if (isShowing.not()) {
            return
        }
        val width = parent.width
        val height = parent.height
        val offset = (width - 2 * barMarginHorizontal - barMarginFirstColumn.toInt() - barWidth) * scrollPosition

        val left = barMarginHorizontal + barMarginFirstColumn.toInt() + offset.toInt()
        val right = left + barWidth
        val bottom = height - barMarginBottom
        val top = bottom - barHeight

        rect.left = left
        rect.top = top
        rect.right = right
        rect.bottom = bottom

        val rx = barHeight / 2
        val ry = barHeight / 2

        c.drawRoundRect(rect, rx, ry, paint)
    }

    override fun updateScrollWeight(wieght: Float) {
        scrollPosition = wieght
        isShowing = true
        invalidate()
    }

    override fun startCountToHide() {
        handler.removeCallbacks(dismissAction)
        handler.postDelayed(dismissAction, barShowingTime)
    }

    fun setBarWidth(barWidth: Float) {
        if (this.barWidth == barWidth) {
            return
        }
        this.barWidth = barWidth
        invalidate()
    }

    fun setBarHeight(barHeight: Float) {
        if (this.barHeight == barHeight) {
            return
        }
        this.barHeight = barHeight
        invalidate()
    }

    fun setBarMarginHorizontal(barMarginHorizontal: Float) {
        if (this.barMarginHorizontal == barMarginHorizontal) {
            return
        }
        this.barMarginHorizontal = barMarginHorizontal
        invalidate()
    }

    fun setBarMarginBottom(barMarginBottom: Float) {
        if (this.barMarginBottom == barMarginBottom) {
            return
        }
        this.barMarginBottom = barMarginBottom
        invalidate()
    }

    fun setBarMarginFirstColum(barMarginFirstColumn: Float) {
        if (this.barMarginFirstColumn == barMarginFirstColumn) {
            return
        }
        this.barMarginFirstColumn = barMarginFirstColumn
        invalidate()
    }

    fun setBarShowingTime(barShowingTime: Long){
        this.barShowingTime = barShowingTime
    }

    fun setBarColor(color: Int) {
        if (paint.color == color){
            return
        }
        paint.color = color
        invalidate()
    }

    private fun invalidate() {
        recyclerView.invalidateItemDecorations()
    }
}