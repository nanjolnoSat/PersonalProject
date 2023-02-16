package com.mishaki.galgamehelper.html.entity.body.tag.table

class HtmlBodyTableThTag: HtmlBodyTableThTdTag() {
    override fun getTagString(): String = TAG

    companion object{
        const val TAG = "th"
    }
}
