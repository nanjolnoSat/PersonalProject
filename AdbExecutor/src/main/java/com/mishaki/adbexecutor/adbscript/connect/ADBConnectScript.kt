package com.mishaki.adbexecutor.adbscript.connect

import com.mishaki.adbexecutor.script.ScriptIO

class ADBConnectScript : ScriptIO(){

    override fun getCommand(): String = COMMAND

    companion object{
        const val COMMAND = "connect"
    }
}