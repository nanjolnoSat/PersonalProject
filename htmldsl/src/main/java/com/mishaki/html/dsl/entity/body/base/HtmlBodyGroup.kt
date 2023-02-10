package com.mishaki.galgamehelper.html.entity.body.base

abstract class HtmlBodyGroup<T: HtmlBody>: HtmlBody {
    protected open var internalBodyList: MutableList<T> = ArrayList()

    open fun addHtmlBody(body: T) {
        internalBodyList.add(body)
    }

    open fun addAllHtmlBody(list: List<T>) {
        internalBodyList.addAll(list)
    }

    open fun removeHtmlBody(body: T) {
        internalBodyList.remove(body)
    }

    open fun clearBodyList() {
        internalBodyList.clear()
    }

    open fun getBodyList(): List<T> {
        return internalBodyList
    }
}
