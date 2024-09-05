package com.mishaki.htmlDSL.entity.body.tag.list

import com.mishaki.htmlDSL.util.generateHtmlCode
import com.mishaki.htmlDSL.entity.body.base.HtmlBody
import com.mishaki.htmlDSL.entity.body.base.HtmlBodyGroup

class HtmlBodyListLITag: HtmlBodyGroup<HtmlBody>(){
    override fun getTagString(): String = TAG

    override fun toHtmlCode(): String {
        return generateHtmlCode()
    }

    override fun getAttributeList(): List<Pair<String, Any>> = emptyList()

    companion object{
        const val TAG = "li"
    }
}