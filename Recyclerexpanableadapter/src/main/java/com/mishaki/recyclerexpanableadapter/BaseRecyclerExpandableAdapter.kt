package com.mishaki.recyclerexpanableadapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

typealias OnParentClickListener = (parentPosition: Int) -> Unit
typealias OnChildClickListener = (parentPosition: Int, childPosition: Int) -> Unit

abstract class BaseRecyclerExpandableAdapter<PARENT_VH : BaseRecyclerExpandableAdapter.BaseViewHolder, CHILD_VH : BaseRecyclerExpandableAdapter.BaseViewHolder> :
    RecyclerView.Adapter<BaseRecyclerExpandableAdapter.BaseViewHolder>() {
    private val viewTypeRecorder = ArrayList<Int>()
    private val realPositionRecorder = ArrayList<RealPosition>()

    init {
        registerDataObserver()
    }

    private fun registerDataObserver() {
        registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                calculateNecessaryData()
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                calculateNecessaryData()
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
                calculateNecessaryData()
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                calculateNecessaryData()
            }

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                calculateNecessaryData()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                calculateNecessaryData()
            }

        })
    }

    private fun calculateNecessaryData() {
        viewTypeRecorder.clear()
        realPositionRecorder.clear()

        for (parentPosition in 0 until getParentCount()) {
            viewTypeRecorder.add(obtainParentViewType(parentPosition))
            realPositionRecorder.add(RealPosition(parentPosition, NO_POSITION))
            if (isDisplayedChildList(parentPosition)) {
                for (childPosition in 0 until getChildCountFromParent(parentPosition)) {
                    viewTypeRecorder.add(obtainChildViewType(parentPosition, childPosition))
                    realPositionRecorder.add(RealPosition(parentPosition, childPosition))
                }
            }
        }
    }

    private var onParentClickListener: OnParentClickListener? = null
    private var onChildClickListener: OnChildClickListener? = null

    // ?????????????????????child list?????????
    private val hideChildListParentPositionList = ArrayList<Int>()

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when {
            isParentViewType(viewType) -> onCreateParentViewHolder(parent, viewType shl 1 shr 1)
            isChildViewType(viewType) -> onCreateChildViewHolder(parent, viewType shl 2 shr 2)
            else -> throw RuntimeException("unknow view type:$viewType")
        }
    }

    // ??????parent???ViewHolder
    protected abstract fun onCreateParentViewHolder(parent: ViewGroup, viewType: Int): PARENT_VH

    // ??????child???ViewHolder
    protected abstract fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): CHILD_VH

    final override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val realPosition = realPositionRecorder[position]
        if (realPosition.childPosition == NO_POSITION) {
            val isDisplayedChildList = isDisplayedChildList(realPosition.parentPosition)
            onBindParentViewHolder(
                holder as PARENT_VH,
                realPosition.parentPosition,
                isDisplayedChildList
            )
            holder.itemView.setOnClickListener {
                onParentClickListener?.invoke(realPosition.parentPosition)
            }
            return
        }
        onBindChildViewHolder(
            holder as CHILD_VH,
            realPosition.parentPosition,
            realPosition.childPosition
        )
        holder.itemView.setOnClickListener {
            onChildClickListener?.invoke(realPosition.parentPosition, realPosition.childPosition)
        }
    }

    // ??????onBindViewHolder????????????view type???parent view type???????????????
    protected abstract fun onBindParentViewHolder(
        viewHolder: PARENT_VH,
        parentPosition: Int,
        isDisplayedChildList: Boolean
    )

    // ??????onBindViewHolder????????????view type???child view type???????????????
    protected abstract fun onBindChildViewHolder(
        viewHolder: CHILD_VH,
        parentPosition: Int,
        childPosition: Int
    )

    final override fun getItemCount(): Int = viewTypeRecorder.size

    // ??????parent count
    protected abstract fun getParentCount(): Int

    // ??????parent position??????child count
    protected abstract fun getChildCountFromParent(parentPosition: Int): Int

    final override fun getItemViewType(position: Int): Int = viewTypeRecorder[position]

    // ??????parent view type??????????????????getParentViewType????????????????????????????????????
    private fun obtainParentViewType(parentPosition: Int): Int {
        val type = getParentViewType(parentPosition)
        checkViewType(type)
        return type or PARENT_VIEW_TYPE
    }

    /**
     * @see MAX_VIEW_TYPE
     * @see checkViewType
     * @return child view type, ????????????child view type ??????
     * ?????????????????? MAX_VIEW_TYPE???????????????????????????
     */
    protected open fun getParentViewType(parentPosition: Int) = DEFAULT_VIEW_TYPE

    /**
     * @see MAX_VIEW_TYPE
     * @see checkViewType
     * @return child view type, ????????????parent view type ??????
     * ?????????????????? MAX_VIEW_TYPE???????????????????????????
     */
    protected open fun getChildViewType(parentPosition: Int, childPosition: Int) = DEFAULT_VIEW_TYPE

    // ??????child view type??????????????????getChildViewType????????????????????????????????????
    private fun obtainChildViewType(parentPosition: Int, childPosition: Int): Int {
        val type = getChildViewType(parentPosition, childPosition)
        checkViewType(type)
        return type or CHILD_VIEW_TYPE
    }

    private fun checkViewType(viewType: Int) {
        if (viewType < 0 || viewType > MAX_VIEW_TYPE) {
            throw IllegalArgumentException("view type :$viewType can't less than 0 or greater than 1073741823(0x3fffffff).")
        }
    }

    protected fun isParentViewType(viewType: Int) = (viewType and PARENT_VIEW_TYPE) == PARENT_VIEW_TYPE

    protected fun isChildViewType(viewType: Int) = (viewType and CHILD_VIEW_TYPE) == CHILD_VIEW_TYPE

    protected fun getRealPosition(position: Int): RealPosition? =
        realPositionRecorder.getOrNull(position)

    // ??????parent position??????child list????????????
    fun isDisplayedChildList(parentPosition: Int) =
        hideChildListParentPositionList.contains(parentPosition).not()

    fun displayAllChildList() {
        hideChildListParentPositionList.clear()
        notifyDataSetChanged()
    }

    fun hideAllChildList() {
        hideChildListParentPositionList.clear()
        hideChildListParentPositionList.addAll(0 until getParentCount())
        notifyDataSetChanged()
    }

    fun scrollToTargetPosition(
        recyclerView: RecyclerView,
        scrollParentPosition: Int,
        scrollChildPosition: Int
    ) {
        if (scrollParentPosition < 0) {
            throw IllegalArgumentException("please give me a greater than 0(contains) parentPosition.")
        }
        var targetPosition = -1
        for (parentPosition in 0 until getParentCount()) {
            targetPosition++
            if (parentPosition == scrollParentPosition) {
                break
            }
            if (isDisplayedChildList(parentPosition)) {
                targetPosition += getChildCountFromParent(parentPosition)
            }
        }
        if (scrollChildPosition != NO_POSITION) {
            recyclerView.scrollToPosition(targetPosition + scrollChildPosition + 1)
        } else {
            recyclerView.scrollToPosition(targetPosition)
        }
    }

    fun displayChildList(parentPosition: Int) {
        if (hideChildListParentPositionList.contains(parentPosition)) {
            hideChildListParentPositionList.remove(parentPosition)
            notifyDataSetChanged()
        }
    }

    fun hideChildList(parentPosition: Int) {
        if (hideChildListParentPositionList.contains(parentPosition).not()) {
            hideChildListParentPositionList.add(parentPosition)
            notifyDataSetChanged()
        }
    }

    fun setOnParentClickListener(onParentClickListener: OnParentClickListener) {
        this.onParentClickListener = onParentClickListener
    }

    fun setOnChildClickListener(onChildClickListener: OnChildClickListener) {
        this.onChildClickListener = onChildClickListener
    }

    protected class RealPosition(val parentPosition: Int, val childPosition: Int)

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        const val NO_POSITION = -1

        // 10000000 00000000 00000000 00000000
        private const val PARENT_VIEW_TYPE = 0x80000000.toInt()

        // 01000000 00000000 00000000 00000000
        private const val CHILD_VIEW_TYPE = 0x40000000

        // ??????????????????[0,CHILD_VIEW_TYPE-1]
        // 00111111 11111111 11111111 11111111
        private const val MAX_VIEW_TYPE = 0x3fffffff

        const val DEFAULT_VIEW_TYPE = 0
    }
}