package com.mishaki.galgamehelper.html.entity.body.tag.table

import com.mishaki.galgamehelper.html.dsl.generateAttributeString
import com.mishaki.galgamehelper.html.dsl.generateValueAndAttributeCode
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyAttribute
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyIdAttribute
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyWidthHeightAttribute
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodyValueAndBody

abstract class HtmlBodyTableThTdTag: HtmlBody, HtmlBodyValueAndBody<String?, HtmlBody>, HtmlBodyIdAttribute, HtmlBodyWidthHeightAttribute {
    override var id: String? = null
    override var value: String? = null
    override var valueBody: HtmlBody? = null
    var colspan: Int = 1
    override var width: String? = null
    override var height: String? = null

    override fun toHtmlCode(): String {
        return generateValueAndAttributeCode {
            attributeString = generateAttributeString(
                getIdAttribute(),
                HtmlBodyAttribute.COL_SPAN to colspan,
                getWidthAttribute(),
                getHeightAttribute(),
            )
            val value = value?.takeIf {it.isNotEmpty()} ?: valueBody?.toHtmlCode()
            if (value != null) {
                valueString = value
            }
        }
    }
}
