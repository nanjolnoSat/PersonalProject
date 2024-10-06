package com.mishaki.adbexecutor.adbscript.shell.am

import com.mishaki.adbexecutor.script.IADBIntent
import com.mishaki.adbexecutor.script.ScriptIO

class ADBBroadcastScript : ScriptIO(), IADBIntent {

    override fun getCommand(): String = COMMAND

    companion object{
        const val COMMAND = "broadcast"
    }
}