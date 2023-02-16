package com.mishaki.galgamehelper.html.entity

import com.mishaki.galgamehelper.html.util.generateHtmlCode
import com.mishaki.galgamehelper.html.entity.body.HtmlBodyRoot
import com.mishaki.galgamehelper.html.entity.header.HtmlHeaderRoot
import com.mishaki.galgamehelper.html.entity.base.HtmlTag

class HtmlRoot: HtmlTag {
    var header: HtmlHeaderRoot? = null
    var body: HtmlBodyRoot? = null

    override fun getTagString(): String = TAG

    override fun toHtmlCode(): String {
        return generateHtmlCode(listOfNotNull(header, body))
    }

    override fun getAttributeList(): List<Pair<String, Any>> = emptyList()

    companion object {
        const val TAG = "html"
    }
}
