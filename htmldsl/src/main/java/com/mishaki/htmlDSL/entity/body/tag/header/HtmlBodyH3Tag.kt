package com.mishaki.htmlDSL.entity.body.tag.header

class HtmlBodyH3Tag: HtmlBodyHTagBase() {
    override fun getTagString(): String = TAG

    companion object{
        const val TAG = "h3"
    }
}
