package com.mishaki.tableview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import java.lang.ref.WeakReference

class RecyclerItemTableHorizontalGestureLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    TableHorizontalGestureLayout(context, attrs, defStyleAttr) {
    private var scrollManager: ScrollManager? = null
    private var clickArea: View? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (clickArea == null) {
            scrollManager?.addCandidate(this)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (clickArea == null) {
            scrollManager?.removeCandidate(this)
        }
    }

    fun setScrollManagerAndClickArea(scrollManager: ScrollManager, clickArea: View?) {
        this.scrollManager = scrollManager
        this.clickArea = clickArea
        onScrolledChangeListener = RecyclerItemOnScrolleChangedListener(scrollManager)
        clickArea?.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                scrollManager.also {
                    it.addCandidate(v)
                }
            }

            override fun onViewDetachedFromWindow(v: View) {
                scrollManager.also {
                    it.removeCandidate(v)
                }
            }
        })
    }

    private class RecyclerItemOnScrolleChangedListener(scrollManager: ScrollManager) : OnScrolleChangedListener {
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
        }

        override fun draggingEnd() {
            scrollManagerWeakRef.get()?.draggingEnd()
        }
    }
}