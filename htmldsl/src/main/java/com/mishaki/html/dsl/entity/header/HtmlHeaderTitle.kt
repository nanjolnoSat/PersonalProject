package com.mishaki.galgamehelper.html.entity.header

import com.mishaki.galgamehelper.html.dsl.generateValueAndAttributeCode
import com.mishaki.galgamehelper.html.entity.header.base.HtmlHeader

class HtmlHeaderTitle(val title: String) : HtmlHeader {
    override fun getTagString(): String = "title"

    override fun toHtmlCode(): String {
        return generateValueAndAttributeCode {
            // TODO if need
            attributeString = ""
            valueString = title
        }
    }
}
