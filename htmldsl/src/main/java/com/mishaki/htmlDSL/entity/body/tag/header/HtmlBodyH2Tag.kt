package com.mishaki.htmlDSL.entity.body.tag.header

class HtmlBodyH2Tag: HtmlBodyHTagBase() {
    override fun getTagString(): String = TAG

    companion object{
        const val TAG = "h2"
    }
}
