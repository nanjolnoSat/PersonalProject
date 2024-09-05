package com.mishaki.htmlDSL.entity.body.tag.table

class HtmlBodyTableTHead: HtmlBodyTableTHeadTBodyTFoot(){
    override fun getTagString(): String = TAG

    companion object{
        const val TAG = "thead"
    }
}
