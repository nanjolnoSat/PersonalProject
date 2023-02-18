package com.mishaki.personalproject.recyclerexpandableadapter

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mishaki.personalproject.R
import com.mishaki.recyclerexpanableadapter.BaseRecyclerExpandableAdapter
import com.mishaki.recyclerexpanableadapter.FloatItemDecoration

class SecondListAdapter : BaseRecyclerExpandableAdapter<SecondListAdapter.ParentViewHolder, SecondListAdapter.ChildViewHolder>(),
    FloatItemDecoration.StickHeaderInterface {

    companion object {
        private const val HEADER_1_PARENT_VIEW_TYPE = 1
        private const val HEADER_2_PARENT_VIEW_TYPE = 2
        private const val NORMAL_PARENT_VIEW_TYPE = 3

        private const val HEADER_1_PARENT_POSITION = 0
        private const val HEADER_2_PARENT_POSITION = 1

        private const val HEADER_1_PARENT_VIEW = 1
        private const val HEADER_2_PARENT_VIEW = 1
    }

    val parentList = ArrayList<String>()
    val childMap = HashMap<String, Int>()

    init {
        setOnParentClickListener { parentPosition ->
            if (parentPosition == HEADER_1_PARENT_POSITION || parentPosition == HEADER_2_PARENT_POSITION) {
                return@setOnParentClickListener
            }
            if (isDisplayedChildList(parentPosition)) {
                hideChildList(parentPosition)
            } else {
                displayChildList(parentPosition)
            }
        }
    }

    override fun onCreateParentViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder =
        when (viewType) {
            HEADER_1_PARENT_VIEW_TYPE -> Header1ParentViewHolder(FrameLayout(parent.context))
            HEADER_2_PARENT_VIEW_TYPE -> Header2ParentViewHolder(FrameLayout(parent.context))
            else -> NormalParentViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_second_list, parent, false)
            )
        }

    override fun getParentViewType(parentPosition: Int): Int {
        return when (parentPosition) {
            HEADER_1_PARENT_POSITION -> HEADER_1_PARENT_VIEW_TYPE
            HEADER_2_PARENT_POSITION -> HEADER_2_PARENT_VIEW_TYPE
            else -> NORMAL_PARENT_VIEW_TYPE
        }
    }

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder =
        ChildViewHolder(
            FrameLayout(parent.context)
        )

    override fun onBindParentViewHolder(viewHolder: ParentViewHolder, parentPosition: Int, isDisplayedChildList: Boolean) {
        when (getParentViewType(parentPosition)) {
            HEADER_1_PARENT_VIEW_TYPE -> {
                val vh = viewHolder as Header1ParentViewHolder
                vh.textView.text = "header_1"
            }
            HEADER_2_PARENT_VIEW_TYPE -> {
                val vh = viewHolder as Header2ParentViewHolder
                vh.textView.text = "header_2"
            }
            else -> {
                val realPosition = getRealParentPosition(parentPosition)
                val vh = viewHolder as NormalParentViewHolder
                vh.textView.text = parentList[realPosition]
            }
        }
    }

    override fun onBindChildViewHolder(viewHolder: ChildViewHolder, parentPosition: Int, childPosition: Int) {
    }

    override fun getParentCount(): Int =
        HEADER_1_PARENT_VIEW + HEADER_2_PARENT_VIEW + parentList.size

    override fun getChildCountFromParent(parentPosition: Int): Int =
        when (parentPosition) {
            HEADER_1_PARENT_POSITION, HEADER_2_PARENT_POSITION -> 0
            else -> childMap[parentList[getRealParentPosition(parentPosition)]] ?: 0
        }

    private fun getRealParentPosition(parentPosition: Int) = parentPosition - HEADER_1_PARENT_VIEW - HEADER_2_PARENT_VIEW

    open class ParentViewHolder(itemView: View) : BaseRecyclerExpandableAdapter.BaseViewHolder(itemView)

    class Header1ParentViewHolder(itemView: FrameLayout) :
        ParentViewHolder(itemView) {
        val textView = TextView(itemView.context).also {
            it.setTextColor(0xff000000.toInt())
            it.textSize = 40f
            it.setPadding(10, 10, 10, 10)
        }

        init {
            itemView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            itemView.addView(textView)
        }
    }

    class Header2ParentViewHolder(itemView: FrameLayout) :
        ParentViewHolder(itemView) {
        val textView = TextView(itemView.context).also {
            it.setTextColor(0xffff0000.toInt())
            it.textSize = 60f
            it.setPadding(10, 10, 10, 10)
        }

        init {
            itemView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            itemView.addView(textView)
        }
    }

    class NormalParentViewHolder(itemView: View) : ParentViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text)
    }

    class ChildViewHolder(itemView: FrameLayout) : BaseRecyclerExpandableAdapter.BaseViewHolder(itemView) {
        val imageView = ImageView(itemView.context).also {
            val dp40 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, itemView.context.resources.displayMetrics).toInt()
            it.layoutParams = FrameLayout.LayoutParams(dp40, dp40)
            it.setPadding(10, 10, 10, 10)
            it.setImageResource(R.mipmap.ic_launcher)
        }

        init {
            itemView.layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            itemView.addView(imageView)
        }
    }

    override fun isStick(position: Int): Boolean {
        return getRealPosition(position)?.let { realPosition ->
            if (realPosition.childPosition != NO_POSITION) {
                return false
            }
            realPosition.parentPosition != HEADER_1_PARENT_POSITION && realPosition.parentPosition != HEADER_2_PARENT_POSITION
        } ?: false
    }
}