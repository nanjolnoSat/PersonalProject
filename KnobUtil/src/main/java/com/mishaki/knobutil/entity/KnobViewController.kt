package com.mishaki.knobutil.entity

import android.view.View
import com.mishaki.knobutil.KnobAction
import java.util.*

class KnobViewController {
    val nextKnobViewIdList: MutableMap<KnobAction, Int> = EnumMap(KnobAction::class.java)
    var nextKnobAfterClickId: Int = View.NO_ID
    var isAutoUpdateIndexWhenUserClick: Boolean = true
    // 默认情况下，如果一个View不处理旋钮焦点事件，会自动将事件分发给下一个View，直到找到目标View
    // 如果不希望自动将旋钮事件分发给下一个View，则设置为false
    var isAutoDispatchKnobFocusActionToNextView: Boolean = true
    var isAutoRequestCurrentPage: Boolean = true
    var isAutoScrollWhenViewFocused: Boolean = true
    var actionChainType: KnobActionChainType = KnobActionChainType.NONE

    fun clear() {
        nextKnobViewIdList.clear()
        nextKnobAfterClickId = View.NO_ID
    }
}

enum class KnobActionChainType{
    NONE, HORIZONTAL, VERTICAL, FULL;

    fun isHorizontalChainType() = this == HORIZONTAL || this == FULL

    fun isVerticalChainType() = this == VERTICAL || this == FULL
}
