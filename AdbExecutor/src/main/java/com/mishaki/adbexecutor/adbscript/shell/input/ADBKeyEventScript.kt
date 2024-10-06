package com.mishaki.adbexecutor.adbscript.shell.input

import com.mishaki.adbexecutor.script.ScriptIO

class ADBKeyEventScript: ScriptIO() {

    override fun getCommand(): String = COMMAND

    companion object {
        const val COMMAND = "keyevent"
    }
}

enum class KEYCODE(val code: String) {
    KEYCODE_BACK("KEYCODE_BACK"),
    KEYCODE_HOME("KEYCODE_HOME")
}