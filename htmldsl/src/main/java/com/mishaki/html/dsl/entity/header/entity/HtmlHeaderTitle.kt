package com.mishaki.galgamehelper.html.entity.header.entity

import com.mishaki.galgamehelper.html.util.generateHtmlCode
import com.mishaki.galgamehelper.html.entity.header.base.HtmlHeader

class HtmlHeaderTitle(val title: String): HtmlHeader {
    override fun getTagString(): String = TAG

    override fun toHtmlCode(): String {
        return generateHtmlCode(title)
    }

    override fun getAttributeList(): List<Pair<String, Any>> = emptyList()

    companion object{
        const val TAG = "title"
    }
}
