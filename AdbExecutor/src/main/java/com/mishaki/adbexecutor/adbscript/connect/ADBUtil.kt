package com.mishaki.adbexecutor.script.adbscript.connect

import com.mishaki.adbexecutor.script.ScriptIO
import com.mishaki.adbexecutor.script.adbscript.ADBScript

infix fun ADBScript.connect(ip: String) {
    ADBConnectScript().also {
        scriptInput?.also(it::init)
        it.scriptInput?.also { scriptInput ->
            scriptInput.append(ScriptIO.SPACE)?.append(ip)
        }
        it.output()
    }
}

infix fun ADBScript.disconnect(ip: String) {
    ADBDisconnectScript().also {
        scriptInput?.also(it::init)
        it.scriptInput?.also { scriptInput ->
            scriptInput.append(ScriptIO.SPACE)?.append(ip)
        }
        it.output()
    }
}
