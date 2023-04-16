package com.mishaki.tableview

import android.content.Context
import android.util.AttributeSet
import android.view.*
import android.widget.LinearLayout

@Suppress("LeakingThis")
open class TableHorizontalGestureLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr), View.OnTouchListener {
    private var scrollState: Int = SCROLL_STATE_IDLE

    private var maximumFlingVelocity = 8000
    private var minimumFlingVelocity = 50

    private var initialTouchX: Int = 0
    private var lastTouchX: Int = 0
    private var velocityTracker: VelocityTracker? = null

    private var touchSlop = 0

    var onScrolledChangeListener: OnScrolleChangedListener? = null

    init {
        val vc = ViewConfiguration.get(context)
        maximumFlingVelocity = vc.scaledMaximumFlingVelocity
        minimumFlingVelocity = vc.scaledMinimumFlingVelocity
        touchSlop = vc.scaledTouchSlop
        orientation = HORIZONTAL
        setOnTouchListener(this)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                initialTouchX = (ev.x + 0.5f).toInt()
                lastTouchX = initialTouchX
                initOrResetVelocityTracker()
                velocityTracker?.addMovement(ev)
                scrollState = if (onScrolledChangeListener?.isScrollerFinished()
                        ?.not() == true
                ) SCROLL_STATE_DRAGGING else SCROLL_STATE_IDLE
            }
            MotionEvent.ACTION_MOVE -> {
                val x = (ev.x + 0.5f).toInt()
                val dx = x - initialTouchX
                lastTouchX = x
                if (Math.abs(dx) > touchSlop) {
                    initVelocityTrackerIfNoExits()
                    velocityTracker?.addMovement(ev)
                    scrollState = SCROLL_STATE_DRAGGING
                    parent.requestDisallowInterceptTouchEvent(true)
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                scrollState = SCROLL_STATE_IDLE
                onScrolledChangeListener?.draggingEnd()
            }
        }
        return scrollState == SCROLL_STATE_DRAGGING
    }

    override fun onTouch(v: View?, ev: MotionEvent): Boolean {
        initVelocityTrackerIfNoExits()
        velocityTracker?.addMovement(ev)
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                onScrolledChangeListener?.checkAndAbortAnimation()
            }
            MotionEvent.ACTION_MOVE -> {
                val x = (ev.x + 0.5f).toInt()
                val dx = lastTouchX - x
                if (scrollState != SCROLL_STATE_DRAGGING && Math.abs(dx) > touchSlop) {
                    scrollState = SCROLL_STATE_DRAGGING
                    parent.requestDisallowInterceptTouchEvent(true)
                }
                if (scrollState == SCROLL_STATE_DRAGGING) {
                    lastTouchX = x
                    onScrolledChangeListener?.onScrollChange(dx.toFloat())
                }
            }
            MotionEvent.ACTION_UP -> {
                if (scrollState == SCROLL_STATE_DRAGGING) {
                    val velocityTracker = velocityTracker
                    velocityTracker?.computeCurrentVelocity(1000, maximumFlingVelocity.toFloat())
                    val initialVelocity = velocityTracker?.xVelocity?.toInt() ?: 0
                    if (Math.abs(initialVelocity) > minimumFlingVelocity) {
                        onScrolledChangeListener?.onFling(-initialVelocity)
                    } else {
                        postInvalidateOnAnimation()
                    }
                }
                recycler()
            }
            MotionEvent.ACTION_CANCEL -> {
                recycler()
            }
        }
        return true
    }

    private fun recycler() {
        scrollState = SCROLL_STATE_IDLE
        onScrolledChangeListener?.draggingEnd()
        recycleVelocityTracker()
    }

    private fun recycleVelocityTracker() {
        velocityTracker?.recycle()
        velocityTracker = null
    }

    private fun initOrResetVelocityTracker() {
        velocityTracker?.also {
            it.clear()
        } ?: kotlin.run {
            velocityTracker = VelocityTracker.obtain()
        }
    }

    private fun initVelocityTrackerIfNoExits() {
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain()
        }
    }

    override fun computeScroll() {
        if (this.childCount > 1) {
            onScrolledChangeListener?.onComputeScroll()
        }
    }

    interface OnScrolleChangedListener {
        fun isScrollerFinished(): Boolean
        fun checkAndAbortAnimation()
        fun onScrollChange(distanceX: Float)
        fun onFling(velocityX: Int)
        fun onComputeScroll()
        fun draggingEnd()
    }

    companion object {
        private const val SCROLL_STATE_DRAGGING = 1
        private const val SCROLL_STATE_IDLE = 0
    }
}