package com.mishaki.knobutil.util

open class CleanableArrayList<T: Cleanable> : ArrayList<T>() {
    override fun clear() {
        forEach(Cleanable::clear)
        super.clear()
    }
}