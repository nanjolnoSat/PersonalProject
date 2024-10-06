package com.mishaki.adbexecutor.adbscript.shell.input

import com.mishaki.adbexecutor.adbscript.shell.ADBShellChild

open class ADBInputScript : ADBShellChild(){
    override fun getCommand(): String = COMMAND

    companion object{
        const val COMMAND = "input"
    }
}