package com.mishaki.adbexecutor.script

import java.lang.StringBuilder

abstract class ScriptIO {
    var scriptInput: StringBuilder? = null

    fun init(scriptInput: StringBuilder) {
        scriptInput.append(SPACE).append(getCommand())
        this.scriptInput = scriptInput
    }

    fun output() {
        scriptInput?.also(CommandUtil::execute) ?: println("script is null")
    }

    abstract fun getCommand(): String

    companion object {
        const val SPACE = " "
    }
}