package com.mishaki.galgamehelper.html.entity.body.tag.table

class HtmlBodyTableTdTag: HtmlBodyTableThTdTag() {
    override fun getTagString(): String = TAG

    companion object {
        const val TAG = "td"
    }
}
