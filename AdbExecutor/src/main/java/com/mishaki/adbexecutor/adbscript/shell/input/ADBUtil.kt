package com.mishaki.adbexecutor.adbscript.shell.input

import com.mishaki.adbexecutor.adbscript.util.Wrapper
import com.mishaki.adbexecutor.adbscript.util.Wrapper.Companion.toWrapper
import com.mishaki.adbexecutor.adbscript.util.LazyLoader

infix fun ADBInputScript.tap(x: Int): ADBTapScript {
    return ADBTapScript().also { tap ->
        scriptInput?.apply {
            tap.init(this)
            add(x)
        }
    }
}

infix fun LazyLoader<ADBInputScript>.tap(x: Int): ADBTapScript {
    return load().tap(x)
}

infix fun ADBTapScript.withY(y: Int) {
    scriptInput?.apply {
        add(y)
    }
    output()
}

infix fun ADBInputScript.swipe(x: Int): ADBSwipeFirstPointScript {
    return ADBSwipeFirstPointScript().also { swipe ->
        scriptInput?.apply {
            swipe.init(this)
            add(x)
        }
    }
}

infix fun LazyLoader<ADBInputScript>.swipe(x: Int): ADBSwipeFirstPointScript {
    return load().swipe(x)
}

infix fun ADBSwipeFirstPointScript.withY(y: Int): ADBSwipeLastPointScript {
    return ADBSwipeLastPointScript().also { last ->
        scriptInput?.apply {
            last.init(this)
            add(y)
        }
    }
}

infix fun ADBSwipeLastPointScript.lastPoint(x: Int): Wrapper<ADBSwipeLastPointScript> {
    scriptInput?.apply {
        add(x)
    }
    return toWrapper()
}

infix fun Wrapper<ADBSwipeLastPointScript>.withY(y: Int): ADBSwipeTimeScript {
    return ADBSwipeTimeScript().also { time ->
        get().scriptInput?.apply {
            time.init(this)
            add(y)
        }
    }
}

infix fun ADBSwipeTimeScript.swipeTime(ms: Long) {
    scriptInput?.apply {
        add(ms)
    }
    output()
}

infix fun ADBInputScript.keyevent(keyCode: KEYCODE) {
    ADBKeyEventScript().also { keyEvent ->
        scriptInput?.apply {
            keyEvent.init(this)
            add(keyCode.code)
        }
    }.output()
}

infix fun LazyLoader<ADBInputScript>.keyevent(keyCode: KEYCODE) {
    load().keyevent(keyCode)
}
