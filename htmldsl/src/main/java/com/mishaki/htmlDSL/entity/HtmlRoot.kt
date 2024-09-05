package com.mishaki.htmlDSL.entity

import com.mishaki.htmlDSL.util.generateHtmlCode
import com.mishaki.htmlDSL.entity.body.HtmlBodyRoot
import com.mishaki.htmlDSL.entity.header.HtmlHeadRoot
import com.mishaki.htmlDSL.entity.base.HtmlTag

class HtmlRoot: HtmlTag {
    var header: HtmlHeadRoot? = null
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
