package com.mishaki.adbexecutor.adbscript.shell.am

import com.mishaki.adbexecutor.adbscript.shell.ADBShellChild

class ADBAMScript : ADBShellChild(){

    override fun getCommand(): String = COMMAND

    companion object{
        const val COMMAND = "am"
    }
}