package com.mishaki.galgamehelper.html.entity.body.base

interface HtmlBodyGroup<T: HtmlBody>: HtmlBody {
    fun getBodyList(): MutableList<T>

    fun addHtmlBody(body: T) {
        getBodyList().add(body)
    }

    fun addHtmlBody(bodyAction: () -> T) {
        getBodyList().add(bodyAction())
    }

    fun addAll(list: List<T>){
        getBodyList().addAll(list)
    }

    fun clearBodyList() {
        getBodyList().clear()
    }
}
