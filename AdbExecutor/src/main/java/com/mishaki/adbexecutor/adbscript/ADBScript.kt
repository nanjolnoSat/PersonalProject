package com.mishaki.adbexecutor.script.adbscript

import com.mishaki.adbexecutor.script.ScriptIO

class ADBScript: ScriptIO() {

    fun initADB(scriptInput: StringBuilder) {
        scriptInput.append(COMMAND)
        this.scriptInput = scriptInput
    }

    override fun getCommand(): String = COMMAND

    companion object {
        const val COMMAND = "adb"
        const val SERIAL = "-s"
    }
}