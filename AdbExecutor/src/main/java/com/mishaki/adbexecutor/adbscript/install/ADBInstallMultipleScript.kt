package com.mishaki.adbexecutor.adbscript.install

import com.mishaki.adbexecutor.script.ScriptIO

class ADBInstallMultipleScript: ScriptIO() {

    override fun getCommand(): String = COMMAND

    companion object {
        const val COMMAND = "install-multiple"
    }
}
