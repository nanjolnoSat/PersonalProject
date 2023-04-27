package com.mishaki.adbexecutor.script.adbscript.shell.am

import com.mishaki.adbexecutor.script.ScriptIO
import com.mishaki.adbexecutor.script.adbscript.util.LazyLoader

infix fun ADBAMScript.start(dstPage: String) {
    ADBStartScript().also { start ->
        scriptInput?.also {
            start.init(it)
            it.append(ScriptIO.SPACE)
            it.append(dstPage)
        }
    }.output()
}

infix fun LazyLoader<ADBAMScript>.start(dstPage: String) {
    load().start(dstPage)
}
