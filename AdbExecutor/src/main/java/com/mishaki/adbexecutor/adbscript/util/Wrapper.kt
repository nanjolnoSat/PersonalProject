package com.mishaki.adbexecutor.adbscript.util

@JvmInline
value class Wrapper<T>(private val instance: T) {
    fun get(): T {
        return instance
    }

    companion object {
        fun <T> T.toWrapper() = Wrapper(this)
    }
}