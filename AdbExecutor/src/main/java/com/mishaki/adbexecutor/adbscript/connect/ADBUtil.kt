package com.mishaki.adbexecutor.adbscript.connect

import com.mishaki.adbexecutor.adbscript.ADBScript

infix fun ADBScript.connect(address: String) {
    ADBConnectScript().also {
        scriptInput?.also(it::init)
        it.scriptInput?.apply {
            add(address)
        }
        it.output()
    }
}

infix fun ADBScript.disconnect(address: String) {
    ADBDisconnectScript().also {
        scriptInput?.also(it::init)
        it.scriptInput?.apply {
            add(address)
        }
        it.output()
    }
}

infix fun ADBScript.tcpip(port: Short){
    tcpip(port.toUShort())
}

infix fun ADBScript.tcpip(port: UShort){
    ADBTcpipScript().also {
        scriptInput?.also(it::init)
        it.scriptInput?.apply {
            add(port)
            println(joinToString(" "))
        }
        it.output()
    }
}

fun ADBScript.reboot(){
    scriptInput?.add("reboot")
    output()
}
