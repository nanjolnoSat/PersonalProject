package com.mishaki.galgamehelper.html.entity.body

import com.mishaki.galgamehelper.html.util.generateHtmlCodeByStringList
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodyGroup
import com.mishaki.galgamehelper.html.entity.style.HtmlStyleRoot
import com.mishaki.galgamehelper.html.util.bodyTagListToString

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
