package com.mishaki.htmlDSL.entity.body.tag.list

class HtmlBodyListULTag : HtmlBodyListULOLBaseTag(){
    override fun getTagString(): String = TAG

    companion object{
        const val TAG = "ul"
    }
}