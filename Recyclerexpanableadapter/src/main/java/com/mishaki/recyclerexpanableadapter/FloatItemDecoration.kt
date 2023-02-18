package com.mishaki.recyclerexpanableadapter

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.RuntimeException

class FloatItemDecoration(private val recyclerView: RecyclerView) : RecyclerView.ItemDecoration() {
    interface StickHeaderInterface {
        fun isStick(position: Int): Boolean
    }

    private val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
    private val adapter = recyclerView.adapter ?: throw RuntimeException("please set Decoration after set adapter")
    private val stickHeaderInterface = adapter.let {
        if (it !is StickHeaderInterface) {
            throw RuntimeException("please let your adapter implements StickHeaderInterface")
        }
        adapter as StickHeaderInterface
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        // 获取第一个可见的view和他的position
        val firstChild = parent.getChildAt(0) ?: return
        val position = parent.getChildAdapterPosition(firstChild)
        // 向该view的上面寻找，目的是找出可以stick的view
        for (i in position downTo 0) {
            // 如果找到了
            if (stickHeaderInterface.isStick(i)) {
                var top = 0
                // 这里两个if的作用是：当position的下一个view，即屏幕可见的那个view的下一个view需要stick
                // 的时候，就获取该view的top，并且当该view的top大于的时候，top的值用该view的top
                // 这里的代码很关键，正因为有了这两个if里面的代码，才实现了两个stick view贴在一起
                // 一起向上或向下滚动的效果
                if (position + 1 < adapter.itemCount) {
                    if (stickHeaderInterface.isStick(position + 1)) {
                        val nextChild = parent.getChildAt(1)
                        top = Math.max(linearLayoutManager.getDecoratedTop(nextChild), 0)
                    }
                }
                val holder = adapter.createViewHolder(parent, adapter.getItemViewType(i))
                adapter.bindViewHolder(holder, i)
                // 注意：这里计算的是在i所在位置的view的大小，不是position的位置
                val measureHeight = getMeasureHeight(holder.itemView)
                c.save()
                // 只有当top小于第一个view的高度的时候，并且top大于0，画布才向上滚动
                if (top < measureHeight && top > 0) {
                    c.translate(0f, ((top - measureHeight).toFloat()))
                }
                holder.itemView.draw(c)
                return
            }
        }
    }

    private fun getMeasureHeight(header: View): Int {
        val widthSpec = View.MeasureSpec.makeMeasureSpec(recyclerView.width, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        header.measure(widthSpec, heightSpec)
        header.layout(0, 0, header.measuredWidth, header.minimumHeight)
        return header.measuredHeight
    }
}