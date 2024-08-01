package com.mishaki.knobutil.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mishaki.knobutil.KnobAction
import com.mishaki.knobutil.entity.KnobViewDataHolder

open class KnobRecyclerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    RecyclerView(context, attrs, defStyleAttr), BaseKnobView<KnobViewDataHolder> {

    override val dataHolder: KnobViewDataHolder
        get() {
            val adapter = adapter
            if (adapter is BaseKnobView<*>) {
                return adapter.dataHolder
            }
            return KnobViewDataHolder()
        }

    override fun dispatchKnobFocusAction(action: KnobAction): Boolean {
        val adapter = adapter
        return if (adapter is BaseKnobView<*>) {
            adapter.dispatchKnobFocusAction(action)
        } else false
    }

    override fun onKnobFocused(action: KnobAction) {
        val adapter = adapter
        if (adapter is BaseKnobView<*>) {
            adapter.onKnobFocused(action)
        }
    }

    override fun onClearKnobFocus() {
        val adapter = adapter
        if (adapter is BaseKnobView<*>) {
            adapter.onClearKnobFocus()
        }
    }

    override fun onKnobFocusedInner(action: KnobAction) {
        // 执行的是adapter的方法，这里不需要做任何事情
    }

    override fun onClearKnobFocusInner() {
        // 执行的是adapter的方法，这里不需要做任何事情
    }

    override fun onKnobEnter() {
        val adapter = adapter
        if (adapter is BaseKnobView<*>) {
            adapter.onKnobEnter()
        }
    }

    override fun isInterceptNextKnobAction(action: KnobAction): Boolean {
        val adapter = adapter
        return if (adapter is BaseKnobView<*>) {
            adapter.isInterceptNextKnobAction(action)
        } else false
    }

    override fun onKnobBackPressed(): Boolean {
        val adapter = adapter
        return if (adapter is BaseKnobView<*>) {
            adapter.onKnobBackPressed()
        } else false
    }

    override val view: View = this

    override fun setAccessibilityDelegate(delegate: AccessibilityDelegate?) {
        // KnobFocusUtil调用这个方法是为了监听点击事件，RecyclerView不需要监听点击事件
    }
}