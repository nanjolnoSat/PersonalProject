package com.mishaki.galgamehelper.html.entity.body.tag.general

import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodyValue

class HtmlBodyTextTag: HtmlBody, HtmlBodyValue<String> {
    override var value: String? = null

    override fun getTagString(): String = ""

    override fun toHtmlCode(): String {
        return value ?: ""
    }
}
