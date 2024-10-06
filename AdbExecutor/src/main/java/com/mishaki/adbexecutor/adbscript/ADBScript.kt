package com.mishaki.adbexecutor.adbscript

import com.mishaki.adbexecutor.script.ScriptIO


class ADBScript: ScriptIO() {
    fun initADB(scriptInput: ArrayList<Any>) {
        scriptInput.add(COMMAND)
        this.scriptInput = scriptInput
    }

    override fun getCommand(): String = COMMAND

    companion object {
        const val COMMAND = "adb"
        const val SERIAL = "-s"
    }
}
