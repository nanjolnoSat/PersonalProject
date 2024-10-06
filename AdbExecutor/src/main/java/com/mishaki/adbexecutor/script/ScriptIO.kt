package com.mishaki.adbexecutor.script

import com.mishaki.adbexecutor.CommandUtil

abstract class ScriptIO {
    var scriptInput: ArrayList<Any>? = null

    fun init(scriptInput: ArrayList<Any>) {
        scriptInput.add(getCommand())
        this.scriptInput = scriptInput
    }

    fun output() {
        val command = scriptInput?.joinToString(" ")
        println(command)
        command?.also(CommandUtil::execute) ?: println("script is null")
    }

    abstract fun getCommand(): String
}