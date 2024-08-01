package com.mishaki.knobutil

import com.mishaki.knobutil.util.KnobFocusEvent

enum class KnobAction {
    LEFT,
    RIGHT,
    UP,
    DOWN,
    KNOB_LEFT,
    KNOB_RIGHT,
    ENTER,
    ACTIVE;

    companion object {
        private val knobActionList = hashMapOf(
            KnobFocusEvent.KEY_LEFT to LEFT,
            KnobFocusEvent.KEY_RIGHT to RIGHT,
            KnobFocusEvent.KEY_UP to UP,
            KnobFocusEvent.KEY_DOWN to DOWN,
            KnobFocusEvent.KEY_KNOB_LEFT to KNOB_LEFT,
            KnobFocusEvent.KEY_KNOB_RIGHT to KNOB_RIGHT,
            KnobFocusEvent.KEY_ENTER to ENTER,
        )

        fun get(value: Int): KnobAction? = knobActionList[value]
    }
}