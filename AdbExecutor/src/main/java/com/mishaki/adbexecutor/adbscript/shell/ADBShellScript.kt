package com.mishaki.adbexecutor.script.adbscript.shell

import com.mishaki.adbexecutor.script.ScriptIO

class ADBShellScript: ScriptIO(){
    init {
        if(getInstance() == null) {
            threadLocal.set(this)
        }
    }

    override fun getCommand(): String = COMMAND

    fun getScriputInputClone(): StringBuilder? = scriptInput?.let { StringBuilder(it) }

    protected fun finalize() {
        close()
    }

    companion object {
        const val COMMAND = "shell"

        private val threadLocal = ThreadLocal<ADBShellScript>()
        fun getInstance(): ADBShellScript? = threadLocal.get()

        fun close() {
            threadLocal.set(null)
        }
    }
}