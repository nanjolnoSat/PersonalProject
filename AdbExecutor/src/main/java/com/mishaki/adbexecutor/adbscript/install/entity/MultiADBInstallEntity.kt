package com.mishaki.adbexecutor.adbscript.install.entity

class MultiADBInstallEntity: BaseADBInstallEntity() {
    private val apkFilePathList = ArrayList<String>()

    fun addPOption() {
        paramsList.add("-p")
    }

    fun addAPKFilePath(path: String) {
        apkFilePathList.add(path)
    }

    override fun getScript(): String {
        val superResult = super.getScript()
        val apkFilePathScript = apkFilePathList.joinToString(" ")
        return superResult + apkFilePathScript
    }
}
