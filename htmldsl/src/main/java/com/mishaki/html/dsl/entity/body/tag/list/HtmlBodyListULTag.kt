package com.mishaki.galgamehelper.html.entity.body.tag.list

class HtmlBodyListULTag :HtmlBodyListULOLBaseTag(){
    override fun getTagString(): String = TAG

    companion object{
        const val TAG = "ul"
    }
}