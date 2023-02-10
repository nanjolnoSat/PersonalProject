package com.mishaki.galgamehelper.html.entity.body.tag.general

import com.mishaki.galgamehelper.html.util.generateHtmlCode
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody

class HtmlBodyBrTag: HtmlBody {
    override fun getTagString(): String = TAG

    override fun toHtmlCode(): String {
        return generateHtmlCode()
    }

    override fun getAttributeList(): List<Pair<String, Any>> = emptyList()

    companion object{
        const val TAG = "br"
    }
}
