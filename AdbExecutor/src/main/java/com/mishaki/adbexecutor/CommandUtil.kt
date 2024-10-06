package com.mishaki.adbexecutor

import java.io.BufferedReader
import java.io.InputStreamReader

object CommandUtil {
    private const val ADB_START = "adb"

    fun executeADB(command: CharSequence) {
        execute("$ADB_START $command")
    }

    fun execute(command: CharSequence) {
        val process = Runtime.getRuntime().exec(command.toString())
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        var line: String?
        while(reader.readLine().also { line = it } != null) {
            println(line)
        }
    }
}