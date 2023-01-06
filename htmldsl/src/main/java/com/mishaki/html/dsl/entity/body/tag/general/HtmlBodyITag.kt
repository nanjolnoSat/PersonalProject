package com.mishaki.galgamehelper.html.entity.body.tag.general

import com.mishaki.galgamehelper.html.dsl.generateAttributeString
import com.mishaki.galgamehelper.html.dsl.generateValueAndAttributeCode
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyIdAttribute
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodyValue

class HtmlBodyITag(): HtmlBody, HtmlBodyIdAttribute, HtmlBodyValue<String> {
    override var id: String? = null
    override var value: String? = null

    override fun getTagString(): String = "i"

    override fun toHtmlCode(): String {
        return generateValueAndAttributeCode {
            attributeString = generateAttributeString(
                getIdAttribute()
            )
            value?.also {
                valueString = it
            }
        }
    }
}
