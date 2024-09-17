package com.mishaki.htmlDSL.entity.body.tag.header

class HtmlBodyH6Tag: HtmlBodyHTagBase() {
    override fun getTagString(): String = TAG

    companion object{
        const val TAG = "h6"
    }
}
