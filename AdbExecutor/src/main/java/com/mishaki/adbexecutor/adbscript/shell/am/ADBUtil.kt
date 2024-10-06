package com.mishaki.adbexecutor.adbscript.shell.am

import com.mishaki.adbexecutor.adbscript.BuildADBIntentTemp
import com.mishaki.adbexecutor.adbscript.buildStart
import com.mishaki.adbexecutor.entity.ADBIntent
import com.mishaki.adbexecutor.adbscript.util.LazyLoader

infix fun ADBAMScript.start(dstPage: String) {
    ADBStartScript().also { start ->
        scriptInput?.apply {
            start.init(this)
            add(dstPage)
        }
    }.output()
}

infix fun LazyLoader<ADBAMScript>.start(dstPage: String) {
    load().start(dstPage)
}

infix fun ADBAMScript.broadcast(intent: ADBIntent) {
    ADBBroadcastScript().also { start ->
        scriptInput?.apply {
            start.init(this)
            add(intent.getScript())
        }
    }.output()
}

infix fun LazyLoader<ADBAMScript>.broadcast(intent: ADBIntent) {
    load().broadcast(intent)
}

infix fun ADBAMScript.broadcast(ignore: buildStart): BuildADBIntentTemp<ADBBroadcastScript> {
    return BuildADBIntentTemp(ADBBroadcastScript().also { broadcast ->
        scriptInput?.apply {
            broadcast.init(this)
        }
    })
}

infix fun LazyLoader<ADBAMScript>.broadcast(ignore: buildStart): BuildADBIntentTemp<ADBBroadcastScript> {
    return load().broadcast(ignore)
}
