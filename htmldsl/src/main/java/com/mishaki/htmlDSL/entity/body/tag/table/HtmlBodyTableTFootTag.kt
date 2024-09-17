package com.mishaki.htmlDSL.entity.body.tag.table

class HtmlBodyTableTFootTag: HtmlBodyTableTHeadTBodyTFoot() {
    override fun getTagString(): String = TAG

    companion object{
        const val TAG = "tfoot"
    }
}
