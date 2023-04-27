package com.mishaki.adbexecutor.script.adbscript.util

class Wrapper<T>(private val instance: T) {
    fun get(): T {
        return instance
    }

    override fun hashCode(): Int {
        return get().hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return if(other is Wrapper<*>) {
            get() == other.get()
        } else {
            get() == other
        }
    }

    companion object {
        fun <T> T.toWrapper() = Wrapper(this)
    }
}