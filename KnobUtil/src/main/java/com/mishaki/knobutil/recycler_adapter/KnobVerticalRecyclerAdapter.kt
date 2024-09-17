package com.mishaki.knobutil.recycler_adapter

import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView

abstract class KnobVerticalRecyclerAdapter<VH : RecyclerView.ViewHolder> : KnobRecyclerAdapter<VH>() {
    override fun scrollToKnobPositionIfNeed() {
        val parent = recyclerView.parent
        if (parent is ScrollView || parent is NestedScrollView) {
            try {
                val child = recyclerView.getChildAt(knobPosition)
                val top = child.top
                if (parent is ScrollView) {
                    parent.smoothScrollTo(0, recyclerView.top + top)
                } else if (parent is NestedScrollView) {
                    parent.smoothScrollTo(0, recyclerView.top + top)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            scrollToKnobPositionInner()
        }
    }
}