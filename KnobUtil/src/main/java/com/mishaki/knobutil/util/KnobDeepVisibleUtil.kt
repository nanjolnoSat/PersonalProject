package com.mishaki.knobutil.util

import android.view.View

object KnobDeepVisibleUtil {
    @JvmStatic
    fun isDeepVisible(view: View): Boolean {
        if (view.visibility == View.GONE) {
            return false
        }
        var parent = view.parent
        while (parent is View) {
            if ((parent as? View)?.visibility == View.GONE) {
                return false
            }
            parent = parent.getParent()
        }
        return true
    }
}