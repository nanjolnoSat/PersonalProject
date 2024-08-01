package com.mishaki.knobutil.recycler_adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mishaki.knobutil.KnobAction
import com.mishaki.knobutil.entity.KnobViewDataHolder
import com.mishaki.knobutil.view.BaseKnobView
import java.util.concurrent.atomic.AtomicBoolean

abstract class KnobRecyclerAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>(), BaseKnobView<KnobViewDataHolder> {
    private val isFirstAttachToRecyclerView = AtomicBoolean(true)
    private val adapterDataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            moveKnobPositionStrategyList.forEach {
                if (it.condition.invoke()) {
                    it.action.invoke()
                }
            }
        }
    }

    lateinit var recyclerView: RecyclerView
    lateinit var context: Context
    @JvmField
    var clickedPosition = -1
    @JvmField
    var knobPosition = -1
    @JvmField
    var isShowKnob = false
    override val dataHolder = KnobViewDataHolder()
    val moveKnobPositionStrategyList: MutableList<MoveKnobPositionAction> = ArrayList()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (isFirstAttachToRecyclerView.getAndSet(false).not()) {
            return
        }
        this.recyclerView = recyclerView
        context = recyclerView.context
        disableAutoScrollWhenViewFocused()
        registerAdapterDataObserver(adapterDataObserver)
        recyclerView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener{
            override fun onViewAttachedToWindow(v: View) {
            }

            override fun onViewDetachedFromWindow(v: View) {
                recyclerView.removeOnAttachStateChangeListener(this)
                unregisterAdapterDataObserver(adapterDataObserver)
                isFirstAttachToRecyclerView.set(true)
            }
        })
    }

    protected open fun disableAutoScrollWhenViewFocused() {
        viewController.isAutoScrollWhenViewFocused = false
    }

    override fun onKnobFocused(action: KnobAction) {
        dataHolder.hasKnobFocus = true
        onKnobFocusedInner(action)
    }

    override fun onClearKnobFocus() {
        dataHolder.hasKnobFocus = false
        onClearKnobFocusInner()
    }

    override fun onClearKnobFocusInner() {
        isShowKnob = false
        if (knobPosition > -1) {
            notifyItemChanged(knobPosition, false)
        }
    }

    abstract fun scrollToKnobPositionIfNeed()

    protected open fun scrollToKnobPositionInner() {
        try {
            recyclerView.smoothScrollToPosition(knobPosition)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun autoMoveKnobPositionToEdgeIfNeed() {
        moveKnobPositionStrategyList.add(MoveKnobPositionAction({ knobPosition >= itemCount }, { refreshKnobPosition(itemCount - 1) }))
    }

    open fun refreshKnobPosition(position: Int) {
        val oldPosition = knobPosition
        knobPosition = position
        notifyItemChanged(oldPosition, false)
        notifyItemChanged(position, true)
    }

    override val view: View
        get() = recyclerView

    class MoveKnobPositionAction(val condition: Function0<Boolean>, val action: Function0<Unit>)
}