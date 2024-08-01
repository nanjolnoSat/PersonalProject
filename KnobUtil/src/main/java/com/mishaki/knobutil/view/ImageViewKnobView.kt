package com.mishaki.knobutil.view

import com.mishaki.knobutil.KnobAction
import com.mishaki.knobutil.entity.KnobViewDataHolder

interface ImageViewKnobView<T: KnobViewDataHolder>: BaseKnobView<T> {
    override fun isInterceptNextKnobAction(action: KnobAction): Boolean = false
}