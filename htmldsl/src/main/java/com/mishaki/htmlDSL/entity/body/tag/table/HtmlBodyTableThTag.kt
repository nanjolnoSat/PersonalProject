package com.mishaki.htmlDSL.entity.body.tag.table

class HtmlBodyTableThTag: HtmlBodyTableThTdTag() {
    override fun getTagString(): String = TAG

    companion object{
        const val TAG = "th"
    }
}
