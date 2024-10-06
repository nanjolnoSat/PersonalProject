package com.mishaki.adbexecutor.adbscript

val adb: ADBScript
    get() = ADBScript().also {
        it.initADB(ArrayList())
    }

infix fun ADBScript.serial(serial: String): ADBScript {
    scriptInput?.apply {
        add(ADBScript.SERIAL)
        add(serial)
    }
    return this
}

fun ADBScript.devices() {
    scriptInput?.apply {
        add("devices")
    }
    output()
}

fun ADBScript.killServer() {
    scriptInput?.apply {
        add("kill-server")
    }
    output()
}

fun ADBScript.startServer() {
    scriptInput?.apply {
        add("start-server")
    }
    output()
}

fun ADBScript.reStartServer() {
    killServer()
    startServer()
}
