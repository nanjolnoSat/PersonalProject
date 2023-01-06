package com.mishaki.galgamehelper.html.entity.body.tag.general

import com.mishaki.galgamehelper.html.dsl.generateCloseTagCode
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody

class HtmlBodyHrTag: HtmlBody {
    override fun getTagString(): String = "hr"

    override fun toHtmlCode(): String {
        return generateCloseTagCode()
    }
}
