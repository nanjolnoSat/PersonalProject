package com.mishaki.adbexecutor.adbscript.connect

import com.mishaki.adbexecutor.script.ScriptIO

class ADBTcpipScript : ScriptIO(){
    override fun getCommand(): String = "tcpip"

}