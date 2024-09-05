package com.mishaki.htmlDSL.entity.body.tag.list

class HtmlBodyListOLTag : HtmlBodyListULOLBaseTag(){
    override fun getTagString(): String = TAG

    companion object{
        const val TAG = "ol"
    }
}