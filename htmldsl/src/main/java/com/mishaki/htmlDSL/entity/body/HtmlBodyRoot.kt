package com.mishaki.htmlDSL.entity.body

import com.mishaki.htmlDSL.util.generateHtmlCodeByStringList
import com.mishaki.htmlDSL.entity.body.base.HtmlBody
import com.mishaki.htmlDSL.entity.body.base.HtmlBodyGroup
import com.mishaki.htmlDSL.entity.style.HtmlStyleRoot
import com.mishaki.htmlDSL.util.bodyTagListToString

class HtmlBodyRoot: HtmlBodyGroup<HtmlBody>() {
    var style: HtmlStyleRoot? = null

    override fun getTagString(): String = TAG

    override fun toHtmlCode(): String {
        val style = style?.toStyleCode()?.takeIf { it.isNotEmpty() } ?: ""
        return generateHtmlCodeByStringList(listOf(style, getBodyList().bodyTagListToString()))
    }

    override fun getAttributeList(): List<Pair<String, Any>> = emptyList()

    companion object{
        const val TAG = "body"
    }
}
