package com.mishaki.galgamehelper.html.entity.body.tag.table

class HtmlBodyTableTBodyTag: HtmlBodyTableTHeadTBodyTFoot(){
    override fun getTagString(): String = TAG

    companion object{
        const val TAG = "tbody"
    }
}
