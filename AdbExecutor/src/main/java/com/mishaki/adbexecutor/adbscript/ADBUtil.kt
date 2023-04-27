package com.mishaki.adbexecutor.script.adbscript

import com.mishaki.adbexecutor.script.ScriptIO
import java.lang.StringBuilder

val adb: ADBScript
    get() = ADBScript().also {
        it.initADB(StringBuilder())
    }

infix fun ADBScript.serial(ip: String): ADBScript {
    scriptInput?.also {
        it.append(ScriptIO.SPACE)
        it.append(ADBScript.SERIAL)
        it.append(ScriptIO.SPACE)
        it.append(ip)
    }
    return this
}

fun ADBScript.devices() {
    scriptInput?.apply {
        append(ScriptIO.SPACE)
        append("devices")
    }
    output()
}

fun ADBScript.killServer() {
    scriptInput?.apply {
        append(ScriptIO.SPACE)
        append("kill-server")
    }
    output()
}

fun ADBScript.startServer() {
    scriptInput?.apply {
        append(ScriptIO.SPACE)
        append("start-server")
    }
    output()
}

fun ADBScript.reStartServer() {
    killServer()
    startServer()
}
