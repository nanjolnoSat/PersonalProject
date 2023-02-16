package com.mishaki.galgamehelper.html.entity.body.tag.list

import com.mishaki.galgamehelper.html.util.generateHtmlCode
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodyGroup

abstract class HtmlBodyListULOLBaseTag: HtmlBodyGroup<HtmlBodyListLITag>() {
    override fun toHtmlCode(): String {
        return generateHtmlCode()
    }

    override fun getAttributeList(): List<Pair<String, Any>> = emptyList()
}