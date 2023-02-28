package com.mishaki.galgamehelper.html.entity.header

import com.mishaki.galgamehelper.html.util.generateHtmlCodeByStringList
import com.mishaki.galgamehelper.html.entity.header.base.HtmlHeader
import com.mishaki.galgamehelper.html.entity.header.base.HtmlHeaderGroup
import com.mishaki.galgamehelper.html.entity.header.entity.HtmlHeaderTitle
import com.mishaki.galgamehelper.html.entity.style.HtmlStyleRoot
import com.mishaki.galgamehelper.html.util.headerTagListToString

class HtmlHeadRoot: HtmlHeaderGroup<HtmlHeader>() {
    var title: HtmlHeaderTitle? = null
    var style: HtmlStyleRoot? = null

    override fun getTagString(): String = TAG

    override fun toHtmlCode(): String {
        val style = style?.toStyleCode()?.takeIf { it.isNotEmpty() } ?: ""
        return generateHtmlCodeByStringList(listOfNotNull(style, title?.toHtmlCode(), getHeaderList().headerTagListToString()))
    }

    override fun getAttributeList(): List<Pair<String, Any>> = emptyList()

    companion object{
        const val TAG = "head"
    }
}
