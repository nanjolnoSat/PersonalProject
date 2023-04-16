package com.mishaki.tableview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicBoolean

abstract class BaseTableHeaderView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {
    private var inflated = AtomicBoolean(false)
    private var scrollManager: ScrollManager? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (inflated.getAndSet(true).not()) {
            inflate(context, getLayoutId(), this).also(::initLayout)
        }
        val scrollLayout = getScrollLayout() ?:return
        scrollManager?.also{
            it.addCandidate(scrollLayout)
        }
        scrollLayout.onScrolledChangeListener = TableHeaderOnScrollChangedListener(scrollManager)
        val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                scrollLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)

                scrollLayout.apply {
                    var measureParent: ViewGroup = this
                    if (childCount == 1 && getChildAt(0) is ViewGroup){
                        measureParent = getChildAt(0) as ViewGroup
                    }
                    var sChildWidth = 0
                    for (i in 0 until measureParent.childCount){
                        sChildWidth += measureParent.getChildAt(i).measuredWidth
                    }
                    scrollManager?.setMaxScrollOffset((sChildWidth - measureParent.measuredWidth).toFloat())
                }
            }
        }
        scrollLayout.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clearScrollLayoutAndManager()
    }

    fun setScrollManager(scrollManager: ScrollManager) {
        this.scrollManager = scrollManager
        getScrollLayout()?.also {
            if (it.onScrolledChangeListener == null){
                it.onScrolledChangeListener = TableHeaderOnScrollChangedListener(scrollManager)
            }
        }
    }

    private fun clearScrollLayoutAndManager(){
        getScrollLayout()?.onScrolledChangeListener = null
        scrollManager?.also {
            it.removeCandidate(this)
            scrollManager = null
        }
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun initLayout(view: View)
    protected abstract fun getScrollLayout(): TableHorizontalGestureLayout?

    private class TableHeaderOnScrollChangedListener(scrollManager: ScrollManager?) : TableHorizontalGestureLayout.OnScrolleChangedListener{
        private val scrollManagerWeakRef = WeakReference(scrollManager)

        override fun isScrollerFinished(): Boolean {
            return scrollManagerWeakRef.get()?.isScrollerFinished() == true
        }

        override fun checkAndAbortAnimation() {
            scrollManagerWeakRef.get()?.checkAndAbortAnimation()
        }

        override fun onScrollChange(distanceX: Float) {
            scrollManagerWeakRef.get()?.apply {
                safeUpdateScrollPosition(distanceX)
                updateScroll()
            }
        }

        override fun onFling(velocityX: Int) {
            scrollManagerWeakRef.get()?.fling(velocityX)
        }

        override fun onComputeScroll() {
            scrollManagerWeakRef.get()?.updateScrollForScroller()
        }

        override fun draggingEnd() {
            scrollManagerWeakRef.get()?.draggingEnd()
        }
    }
}
