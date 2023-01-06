package com.mishaki.galgamehelper.html.entity

import com.mishaki.galgamehelper.html.dsl.generateValueCodeWithStringBuilder
import com.mishaki.galgamehelper.html.entity.body.HtmlBodyRoot
import com.mishaki.galgamehelper.html.entity.header.HtmlHeaderRoot
import com.mishaki.galgamehelper.html.entity.base.HtmlTag

class HtmlRoot: HtmlTag {
    var header: HtmlHeaderRoot? = null
    var body: HtmlBodyRoot? = null

    override fun getTagString(): String = "html"

    override fun toHtmlCode(): String {
        return generateValueCodeWithStringBuilder {
            header?.toHtmlCode()?.let(::append)
            body?.toHtmlCode()?.let(::append)
        }
    }
}
