package com.mishaki.knobutil.util

import android.view.View
import android.view.ViewParent
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.mishaki.knobutil.view.BaseKnobView

class AutoClearViewList : CleanableArrayList<BaseKnobView<*>>() {
    fun bindView(view: View) {
        var lifecycleOwner: LifecycleOwner? = null
        var parent: ViewParent? = view.parent
        while (parent != null) {
            if (parent is LifecycleOwner) {
                lifecycleOwner = parent
                break
            }
            val parentView = parent.let { it as? View }
            if (parentView?.context is LifecycleOwner) {
                lifecycleOwner = parentView.context as LifecycleOwner
                break
            }
            parent = parent.parent
        }
        lifecycleOwner?.lifecycle?.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                lifecycleOwner.lifecycle.removeObserver(this)
                clear()
            }
        })
    }

    protected fun finalize() {
        clear()
    }
}