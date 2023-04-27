package com.mishaki.adbexecutor.script.adbscript.shell

import com.mishaki.adbexecutor.script.adbscript.ADBScript
import com.mishaki.adbexecutor.script.adbscript.shell.am.ADBAMScript
import com.mishaki.adbexecutor.script.adbscript.shell.input.ADBInputScript
import com.mishaki.adbexecutor.script.adbscript.util.LazyLoader

val input: LazyLoader<ADBInputScript>
    get() = LazyLoader {
        ADBInputScript().also { input ->
            ADBShellScript.getInstance()?.also {
                it.getScriputInputClone()?.also(input::init) ?: throw NullPointerException("for the shell, scriptInput variable must not be null")
            } ?: throw NullPointerException("please init shell before call input")
        }
    }

val am: LazyLoader<ADBAMScript>
    get() = LazyLoader {
        ADBAMScript().also { am ->
            ADBShellScript.getInstance()?.also {
                it.getScriputInputClone()?.also(am::init) ?: throw NullPointerException("for the shell, scriptInput variable must not be null")
            } ?: throw NullPointerException("please init shell before call am")
        }
    }

infix fun <T: LazyLoader<S>, S: ADBShellChild> ADBScript.shell(t: T): S {
    ADBShellScript.close()
    ADBShellScript().also {
        scriptInput?.also(it::init)
    }
    t.load()
    ADBShellScript.close()
    return t.load()
}

fun ADBScript.shell(action: () -> Unit) {
    ADBShellScript.close()
    ADBShellScript().also { shell ->
        scriptInput?.also(shell::init)
    }
    action()
    ADBShellScript.close()
}

