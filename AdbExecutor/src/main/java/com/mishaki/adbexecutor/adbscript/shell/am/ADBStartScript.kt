package com.mishaki.adbexecutor.adbscript.shell.am

import com.mishaki.adbexecutor.script.ScriptIO

class ADBStartScript : ScriptIO(){

    override fun getCommand(): String = COMMAND

    companion object{
        const val COMMAND = "start"
    }
}