package com.mishaki.htmlDSL.entity.body.tag.header

class HtmlBodyH1Tag: HtmlBodyHTagBase() {
    override fun getTagString(): String = TAG

    companion object{
        const val TAG = "h1"
    }
}
