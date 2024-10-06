package com.mishaki.adbexecutor.adbscript.shell

import com.mishaki.adbexecutor.script.ScriptIO

class ADBShellScript: ScriptIO(){
    init {
        if(getInstance() == null) {
            threadLocal.set(this)
        }
    }

    override fun getCommand(): String = COMMAND

    fun getScriputInputClone(): ArrayList<Any>? = scriptInput?.let { ArrayList(it) }

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