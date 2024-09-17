package com.mishaki.htmlDSL.entity.header

import com.mishaki.htmlDSL.util.generateHtmlCodeByStringList
import com.mishaki.htmlDSL.entity.header.base.HtmlHeader
import com.mishaki.htmlDSL.entity.header.base.HtmlHeaderGroup
import com.mishaki.htmlDSL.entity.header.entity.HtmlHeaderTitle
import com.mishaki.htmlDSL.entity.style.HtmlStyleRoot
import com.mishaki.htmlDSL.util.headerTagListToString

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
