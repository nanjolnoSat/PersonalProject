package com.mishaki.adbexecutor.adbscript.install

import com.mishaki.adbexecutor.adbscript.install.entity.ADBInstallEntity
import com.mishaki.adbexecutor.adbscript.install.entity.MultiADBInstallEntity
import com.mishaki.adbexecutor.adbscript.ADBScript

infix fun ADBScript.install(apkFilePath: String) {
    ADBInstallScript().also {
        scriptInput?.also(it::init)
        it.scriptInput?.apply {
            add(apkFilePath)
        }
        it.output()
    }
}

infix fun ADBScript.install(adbInstallEntity: ADBInstallEntity) {
    ADBInstallScript().also {
        scriptInput?.also(it::init)
        it.scriptInput?.apply {
            add(adbInstallEntity.getScript())
        }
        it.output()
    }
}

infix fun ADBScript.installMultiple(adbInstallEntity: MultiADBInstallEntity) {
    ADBInstallMultipleScript().also {
        scriptInput?.also(it::init)
        it.scriptInput?.apply {
            add(adbInstallEntity.getScript())
        }
        it.output()
    }
}

infix fun ADBScript.installMultiPackage(adbInstallEntity: MultiADBInstallEntity) {
    ADBInstallMultiPackageScript().also {
        scriptInput?.also(it::init)
        it.scriptInput?.apply {
            add(adbInstallEntity.getScript())
        }
        it.output()
    }
}

infix fun ADBScript.uninstall(packageName: String) {
    ADBUninstallScript().also {
        scriptInput?.also(it::init)
        it.scriptInput?.apply {
            add(packageName)
        }
        it.output()
    }
}

fun buildADBInstall(action: ADBInstallEntity.() -> Unit): ADBInstallEntity {
    return ADBInstallEntity().apply(action)
}

fun buildMultiADBInstall(action: MultiADBInstallEntity.() -> Unit): MultiADBInstallEntity {
    return MultiADBInstallEntity().apply(action)
}
