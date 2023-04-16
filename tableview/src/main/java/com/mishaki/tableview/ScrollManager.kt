package com.mishaki.tableview

import android.content.Context
import android.view.View
import android.widget.OverScroller
import androidx.lifecycle.*

class ScrollManager(context: Context, currentPage: LifecycleOwner? = null) {
    private val minimumScrollOffset = 0.0f
    private var maximumScrollOffset = Float.MAX_VALUE
    private var scrollOffset = minimumScrollOffset
    private val scrollCandidateList = ArrayList<View>()
    private var scroller = OverScroller(context)

    private var scrollBar: HorizontalScrollBar? = null
    private var scrollBarOffset: Float = 0f

    private var isFling = false

    init {
        currentPage?.also {
            currentPage.lifecycle.addObserver(object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    if (source == currentPage && event == Lifecycle.Event.ON_DESTROY) {
                        clearViews()
                        currentPage.lifecycle.removeObserver(this)
                    }
                }
            })
        }
    }

    fun addCandidate(view: View) {
        if (scrollCandidateList.contains(view).not()) {
            scrollCandidateList.add(view)
            view.scrollTo(scrollOffset.toInt(), 0)
        }
    }

    fun removeCandidate(view: View) {
        scrollCandidateList.remove(view)
    }

    fun setMaxScrollOffset(maxOffset: Float) {
        maximumScrollOffset = maxOffset
    }

    fun scrollSpecialView(view: View) {
        view.scrollTo(scrollOffset.toInt(), 0)
    }

    fun safeUpdateScrollPosition(distanceX: Float) {
        scrollOffset += distanceX
        scrollOffset = Math.min(Math.max(scrollOffset, minimumScrollOffset), maximumScrollOffset)
        scrollBarOffset = calculateScrollBarOffset()
    }

    fun updateScroll() {
        scrollCandidateList.forEach {
            it.scrollTo(scrollOffset.toInt(), 0)
            it.postInvalidateOnAnimation()
        }
        scrollBar?.updateScrollWeight(scrollBarOffset)
    }

    fun clearViews() {
        scrollCandidateList.clear()
        clearScrollBar()
    }

    fun setScrollBar(bar: HorizontalScrollBar) {
        scrollBar = bar
    }

    fun clearScrollBar() {
        scrollBar = null
    }

    fun isScrollerFinished(): Boolean {
        return scroller.isFinished
    }

    fun checkAndAbortAnimation() {
        if (isScrollerFinished().not()) {
            scroller.abortAnimation()
        }
    }

    fun fling(velocityX: Int) {
        isFling = true
        scroller.fling(
            scrollOffset.toInt(), 0, velocityX, 0, 0, maximumScrollOffset.toInt(), 0, 0, 0, 0
        )
        updateScroll()
    }

    fun updateScrollForScroller() {
        if (scroller.computeScrollOffset()) {
            val curX = getSafeUpdatePosition(scroller.currX)
            if (curX != scrollOffset.toInt()) {
                scrollOffset = curX.toFloat()
                scrollBarOffset = calculateScrollBarOffset()
            }
            updateScroll()
            if (scrollOffset == minimumScrollOffset || scrollOffset == maximumScrollOffset) {
                scroller.abortAnimation()
                isFling = false
                scrollBar?.startCountToHide()
            }
        } else {
            if (isFling) {
                isFling = false
                scrollBar?.startCountToHide()
            }
        }
    }

    fun draggingEnd() {
        if (isFling.not()) {
            scrollBar?.startCountToHide()
        }
    }

    private fun getSafeUpdatePosition(curX: Int): Int {
        return Math.min(Math.max(curX, minimumScrollOffset.toInt()), maximumScrollOffset.toInt())
    }

    private fun calculateScrollBarOffset(): Float {
        return scrollOffset / (maximumScrollOffset - minimumScrollOffset)
    }

    interface HorizontalScrollBar {
        fun updateScrollWeight(wieght: Float)
        fun startCountToHide()
    }
}