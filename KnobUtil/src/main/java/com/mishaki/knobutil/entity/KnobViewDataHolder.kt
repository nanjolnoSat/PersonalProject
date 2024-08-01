package com.mishaki.knobutil.entity

import android.util.SparseArray

open class KnobViewDataHolder {
    val knobViewController: KnobViewController = KnobViewController()
    val keyedTags: SparseArray<Any> = SparseArray()
    var hasKnobFocus: Boolean = false

    open fun clear() {
        keyedTags.clear()
        knobViewController.clear()
    }
}