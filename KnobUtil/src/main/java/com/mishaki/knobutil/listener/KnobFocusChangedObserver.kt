package com.mishaki.knobutil.listener

import com.mishaki.knobutil.KnobAction

interface KnobFocusChangedObserver {
    /**
     * 这里无需处理旋钮事件，只是用于某些需要监听旋钮变化的需求
     */
    fun onKnobFocusChanged(action: KnobAction)
}