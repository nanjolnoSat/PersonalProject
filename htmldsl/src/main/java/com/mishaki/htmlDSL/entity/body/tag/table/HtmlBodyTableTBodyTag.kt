package com.mishaki.htmlDSL.entity.body.tag.table

class HtmlBodyTableTBodyTag: HtmlBodyTableTHeadTBodyTFoot(){
    override fun getTagString(): String = TAG

    companion object{
        const val TAG = "tbody"
    }
}
