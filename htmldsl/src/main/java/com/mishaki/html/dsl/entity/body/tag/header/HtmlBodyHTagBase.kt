package com.mishaki.galgamehelper.html.entity.body.tag.header

import com.mishaki.galgamehelper.html.dsl.generateAttributeString
import com.mishaki.galgamehelper.html.dsl.generateValueAndAttributeCode
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyIdAttribute
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodyValueAndBody

abstract class HtmlBodyHTagBase: HtmlBody, HtmlBodyIdAttribute, HtmlBodyValueAndBody<String?, HtmlBody> {
    override var id: String? = null
    override var value: String? = null
    override var valueBody: HtmlBody? = null

    override fun toHtmlCode(): String {
        return generateValueAndAttributeCode {
            attributeString = generateAttributeString(
                getIdAttribute()
            )
            val value = value?.takeIf {it.isNotEmpty()} ?: valueBody?.toHtmlCode()
            if (value != null) {
                valueString = value
            }
        }
    }
}
