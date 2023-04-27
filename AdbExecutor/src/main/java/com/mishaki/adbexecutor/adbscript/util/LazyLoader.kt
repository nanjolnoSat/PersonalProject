package com.mishaki.adbexecutor.script.adbscript.util

class LazyLoader<E>(loadAction: () -> E) {
    private var initLoadAction: Function0<E>? = loadAction
    private var entity: E? = null

    fun load(): E {
        val entity1 = entity
        if(entity1 != null) {
            return entity1
        }
        return synchronized(this) {
            val entity2 = entity
            if(entity2 == null) {
                val returnEntity = initLoadAction!!()
                entity = returnEntity
                initLoadAction = null
                returnEntity
            } else {
                entity2
            }
        }
    }
}