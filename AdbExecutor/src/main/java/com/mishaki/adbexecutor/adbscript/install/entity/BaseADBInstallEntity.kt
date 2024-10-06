package com.mishaki.adbexecutor.adbscript.install.entity

open class BaseADBInstallEntity {
    protected val paramsList = ArrayList<String>()

    fun addLOption() {
        paramsList.add("-l")
    }

    fun addROption() {
        paramsList.add("-r")
    }

    fun addTOption() {
        paramsList.add("-t")
    }

    fun addSOption() {
        paramsList.add("-s")
    }

    fun addDOption() {
        paramsList.add("-d")
    }

    fun addGOption() {
        paramsList.add("-g")
    }

    fun setABI(abi: String) {
        paramsList.add("--abi")
        paramsList.add(abi)
    }

    fun addInstantOption() {
        paramsList.add("--instant")
    }

    fun addNoStreamingOption() {
        paramsList.add("--no-streaming")
    }

    fun addStreamingOption() {
        paramsList.add("--streaming")
    }

    fun addFastdeployOption() {
        paramsList.add("--fastdeploy")
    }

    fun addNoFastdeployOption() {
        paramsList.add("--no-fastdeploy")
    }

    fun addForceAgentOption() {
        paramsList.add("--force-agent")
    }

    fun addDateCheckAgent() {
        paramsList.add("--date-check-agent:")
    }

    fun addVersionCheckAgent() {
        paramsList.add("--version-check-agent")
    }

    open fun getScript(): String {
        return paramsList.joinToString(" ")
    }
}
