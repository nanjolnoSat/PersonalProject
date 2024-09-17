package com.mishaki.knobutil.recycler_adapter

import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.RecyclerView

abstract class KnobHorizontalRecyclerAdapter<VH : RecyclerView.ViewHolder> : KnobRecyclerAdapter<VH>() {
    override fun scrollToKnobPositionIfNeed() {
        val parent = recyclerView.parent
        if (parent is HorizontalScrollView) {
            val child = recyclerView.getChildAt(knobPosition)
            val left = child.left
            parent.smoothScrollTo(recyclerView.left + left, 0)
        } else {
            scrollToKnobPositionInner()
        }
    }
}