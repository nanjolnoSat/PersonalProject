package com.mishaki.adbexecutor.script.adbscript.shell.input

import com.mishaki.adbexecutor.script.ScriptIO
import com.mishaki.adbexecutor.script.adbscript.util.Wrapper
import com.mishaki.adbexecutor.script.adbscript.util.Wrapper.Companion.toWrapper
import com.mishaki.adbexecutor.script.adbscript.util.LazyLoader

infix fun ADBInputScript.tap(x: Int): ADBTapScript {
    return ADBTapScript().also { tap ->
        scriptInput?.also {
            tap.init(it)
            it.append(ScriptIO.SPACE)
            it.append(x)
        }
    }
}

infix fun LazyLoader<ADBInputScript>.tap(x: Int): ADBTapScript {
    return load().tap(x)
}

infix fun ADBTapScript.withY(y: Int) {
    scriptInput?.also {
        it.append(ScriptIO.SPACE)
        it.append(y)
    }
    output()
}

infix fun ADBInputScript.swipe(x: Int): ADBSwipeFirstPointScript {
    return ADBSwipeFirstPointScript().also { swipe ->
        scriptInput?.also {
            swipe.init(it)
            it.append(ScriptIO.SPACE)
            it.append(x)
        }
    }
}

infix fun LazyLoader<ADBInputScript>.swipe(x: Int): ADBSwipeFirstPointScript {
    return load().swipe(x)
}

infix fun ADBSwipeFirstPointScript.withY(y: Int): ADBSwipeLastPointScript {
    return ADBSwipeLastPointScript().also { last ->
        scriptInput?.also {
            last.init(it)
            it.append(y)
        }
    }
}

infix fun ADBSwipeLastPointScript.lastPoint(x: Int): Wrapper<ADBSwipeLastPointScript> {
    scriptInput?.also {
        it.append(ScriptIO.SPACE)
        it.append(x)
    }
    return toWrapper()
}

infix fun Wrapper<ADBSwipeLastPointScript>.withY(y: Int): ADBSwipeTimeScript {
    return ADBSwipeTimeScript().also { time ->
        get().scriptInput?.also {
            time.init(it)
            it.append(y)
        }
    }
}

infix fun ADBSwipeTimeScript.swipeTime(ms: Long) {
    scriptInput?.also {
        it.append(ScriptIO.SPACE)
        it.append(ms)
    }
    output()
}

infix fun ADBInputScript.keyevent(keyCode: KEYCODE) {
    ADBKeyEventScript().also { keyEvent ->
        scriptInput?.also {
            keyEvent.init(it)
            it.append(ScriptIO.SPACE)
            it.append(keyCode.code)
        }
    }.output()
}

infix fun LazyLoader<ADBInputScript>.keyevent(keyCode: KEYCODE) {
    load().keyevent(keyCode)
}
