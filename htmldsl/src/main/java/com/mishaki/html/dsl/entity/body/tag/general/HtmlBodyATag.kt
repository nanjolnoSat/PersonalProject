package com.mishaki.galgamehelper.html.entity.body.tag.general

import com.mishaki.galgamehelper.html.dsl.generateAttributeString
import com.mishaki.galgamehelper.html.dsl.generateValueAndAttributeCode
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyAttribute
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyIdAttribute
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodyValueAndBody

class HtmlBodyATag: HtmlBody, HtmlBodyIdAttribute, HtmlBodyValueAndBody<String, HtmlBody> {
    override var id: String? = null
    override var value: String? = null
    override var valueBody: HtmlBody? = null
    var href: String? = null

    override fun getTagString(): String = "a"

    override fun toHtmlCode(): String {
        return generateValueAndAttributeCode {
            attributeString = generateAttributeString(
                getIdAttribute(),
                href?.let {HtmlBodyAttribute.HREF to it},
            )
            val title = value?.takeIf {it.isNotEmpty()} ?: valueBody?.toHtmlCode()
            title?.also {
                valueString = it
            }
        }
    }
}
