package com.mishaki.adbexecutor.adbscript.connect

import com.mishaki.adbexecutor.script.ScriptIO

class ADBDisconnectScript: ScriptIO() {

    override fun getCommand(): String = COMMAND

    companion object {
        const val COMMAND = "disconnect"
    }
}