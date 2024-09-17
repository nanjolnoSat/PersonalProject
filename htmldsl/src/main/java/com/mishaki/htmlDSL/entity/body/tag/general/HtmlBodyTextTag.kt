package com.mishaki.htmlDSL.entity.body.tag.general

import com.mishaki.htmlDSL.entity.body.base.HtmlBody

class HtmlBodyTextTag: HtmlBody {
    var text: String = ""

    override fun getTagString(): String = ""

    override fun toHtmlCode(): String {
        return text
    }

    override fun getAttributeList(): List<Pair<String, Any>> = emptyList()
}
