package com.mishaki.galgamehelper.html.entity.body.tag.list

class HtmlBodyListOLTag :HtmlBodyListULOLBaseTag(){
    override fun getTagString(): String = TAG

    companion object{
        const val TAG = "ol"
    }
}