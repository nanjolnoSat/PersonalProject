package com.mishaki.adbexecutor.script.adbscript.shell.am

import com.mishaki.adbexecutor.script.adbscript.shell.ADBShellChild

class ADBAMScript :ADBShellChild(){

    override fun getCommand(): String = COMMAND

    companion object{
        const val COMMAND = "am"
    }
}