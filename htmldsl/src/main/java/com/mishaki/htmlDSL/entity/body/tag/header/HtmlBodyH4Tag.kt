package com.mishaki.htmlDSL.entity.body.tag.header

class HtmlBodyH4Tag: HtmlBodyHTagBase() {
    override fun getTagString(): String = TAG

    companion object{
        const val TAG = "h4"
    }
}
