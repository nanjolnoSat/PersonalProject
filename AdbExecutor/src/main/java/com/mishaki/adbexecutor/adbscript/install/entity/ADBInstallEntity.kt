package com.mishaki.adbexecutor.adbscript.install.entity

class ADBInstallEntity: BaseADBInstallEntity() {
    private var apkFilePath: String? = null

    fun setApkFilePath(path: String?) {
        this.apkFilePath = path
    }

    override fun getScript(): String {
        val superResult = super.getScript()
        return superResult + (apkFilePath?.let { " $it" } ?: "")
    }
}
