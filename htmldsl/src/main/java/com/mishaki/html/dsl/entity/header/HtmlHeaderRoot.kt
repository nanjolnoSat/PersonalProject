package com.mishaki.galgamehelper.html.entity.header

import com.mishaki.galgamehelper.html.dsl.generateValueAndAttributeCode
import com.mishaki.galgamehelper.html.entity.header.base.HtmlHeader
import com.mishaki.galgamehelper.html.entity.style.HtmlStyleRoot

class HtmlHeaderRoot: HtmlHeader {
    var title: HtmlHeaderTitle? = null
    var style: HtmlStyleRoot? = null

    override fun getTagString(): String = "header"

    override fun toHtmlCode(): String {
        return generateValueAndAttributeCode {
            setValueWithStringBuilder {
                title?.toHtmlCode()?.let(::append)?.append("\n")
                style?.toStyleCode()?.let(::append)
            }
        }
    }
}
