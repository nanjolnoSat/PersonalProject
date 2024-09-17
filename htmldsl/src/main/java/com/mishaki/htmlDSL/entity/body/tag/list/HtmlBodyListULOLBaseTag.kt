package com.mishaki.htmlDSL.entity.body.tag.list

import com.mishaki.htmlDSL.util.generateHtmlCode
import com.mishaki.htmlDSL.entity.body.base.HtmlBodyGroup

abstract class HtmlBodyListULOLBaseTag: HtmlBodyGroup<HtmlBodyListLITag>() {
    override fun toHtmlCode(): String {
        return generateHtmlCode()
    }

    override fun getAttributeList(): List<Pair<String, Any>> = emptyList()
}