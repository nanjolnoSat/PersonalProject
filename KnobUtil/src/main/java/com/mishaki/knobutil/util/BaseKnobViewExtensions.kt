@file:JvmName("BaseKnobViewUtil")

package com.mishaki.knobutil.util

import com.mishaki.knobutil.entity.KnobActionChainType
import com.mishaki.knobutil.view.BaseKnobView

var BaseKnobView<*>.isAutoUpdateIndexWhenUserClick: Boolean
    get() = viewController.isAutoUpdateIndexWhenUserClick
    set(value) {
        viewController.isAutoUpdateIndexWhenUserClick = value
    }

var BaseKnobView<*>.isAutoDispatchKnobFocusActionToNextView: Boolean
    get() = viewController.isAutoDispatchKnobFocusActionToNextView
    set(value) {
        viewController.isAutoDispatchKnobFocusActionToNextView = value
    }

var BaseKnobView<*>.isAutoRequestCurrentPage: Boolean
    get() = viewController.isAutoRequestCurrentPage
    set(value) {
        viewController.isAutoRequestCurrentPage = value
    }

var BaseKnobView<*>.isAutoScrollWhenViewFocused: Boolean
    get() = viewController.isAutoScrollWhenViewFocused
    set(value) {
        viewController.isAutoScrollWhenViewFocused = value
    }

var BaseKnobView<*>.actionChainType: KnobActionChainType
    get() = viewController.actionChainType
    set(value) {
        viewController.actionChainType = value
    }
