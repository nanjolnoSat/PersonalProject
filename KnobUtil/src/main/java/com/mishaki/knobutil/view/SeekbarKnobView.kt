package com.mishaki.knobutil.view

import com.mishaki.knobutil.KnobAction
import com.mishaki.knobutil.entity.KnobViewDataHolder

interface SeekbarKnobView : BaseKnobView<SeekbarKnobView.SeekbarKnobDataHolder> {
    override fun isInterceptNextKnobAction(action: KnobAction): Boolean {
        return dataHolder.isSeekbarEnter
    }

    override fun dispatchKnobFocusAction(action: KnobAction): Boolean {
        dataHolder.hasKnobFocus = true
        return true
    }

    override fun onKnobFocused(action: KnobAction) {
        if (dataHolder.isSeekbarEnter) {
            onKnobFocusedInner(action)
        }
    }

    override fun onKnobEnter() {
        dataHolder.isSeekbarEnter = !dataHolder.isSeekbarEnter
        if (dataHolder.isSeekbarEnter) {
            onEnterSeekbar()
        } else {
            onLeaveSeekbar()
        }
    }

    override fun onClearKnobFocusInner() {
        leaveSeekbar()
    }

    override fun onKnobBackPressed(): Boolean {
        if (dataHolder.isSeekbarEnter) {
            leaveSeekbar()
            return true
        }
        return false
    }

    fun leaveSeekbar() {
        dataHolder.isSeekbarEnter = false
        dataHolder.hasKnobFocus = false
        onLeaveSeekbar()
    }

    fun onEnterSeekbar() {}
    fun onLeaveSeekbar() {}

    class SeekbarKnobDataHolder : KnobViewDataHolder() {
        var isSeekbarEnter = false
    }
}