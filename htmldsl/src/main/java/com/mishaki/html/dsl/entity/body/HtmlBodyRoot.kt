package com.mishaki.galgamehelper.html.entity.body

import com.mishaki.galgamehelper.html.dsl.generateValueCode
import com.mishaki.galgamehelper.html.entity.base.HtmlStyle
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodyGroup
import com.mishaki.galgamehelper.html.entity.style.HtmlStyleRoot
import com.mishaki.galgamehelper.html.util.bodyListToString

class HtmlBodyRoot: HtmlBodyGroup<HtmlBody> {
    private val bodyList = ArrayList<HtmlBody>()
    var style: HtmlStyleRoot? = null

    override fun getBodyList(): MutableList<HtmlBody> = bodyList

    override fun getTagString(): String = "body"

    override fun toHtmlCode(): String {
        val style = style?.toStyleCode()?.takeIf {it.isNotEmpty()} ?: ""
        return generateValueCode {
            "$style${bodyList.bodyListToString()}"
        }
    }
}
