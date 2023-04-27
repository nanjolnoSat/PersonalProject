package com.mishaki.adbexecutor.script.adbscript.shell.input

import com.mishaki.adbexecutor.script.adbscript.shell.ADBShellChild

open class ADBInputScript : ADBShellChild(){
    override fun getCommand(): String = COMMAND

    companion object{
        const val COMMAND = "input"
    }
}