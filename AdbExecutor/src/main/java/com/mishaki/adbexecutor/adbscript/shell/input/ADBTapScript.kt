package com.mishaki.adbexecutor.script.adbscript.shell.input

import com.mishaki.adbexecutor.script.ScriptIO

class ADBTapScript :ScriptIO(){
    override fun getCommand(): String = COMMAND

    companion object{
        const val COMMAND = "tap"
    }
}