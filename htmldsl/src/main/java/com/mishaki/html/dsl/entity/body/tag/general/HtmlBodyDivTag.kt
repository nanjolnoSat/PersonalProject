package com.mishaki.galgamehelper.html.entity.body.tag.general

import com.mishaki.galgamehelper.html.dsl.generateAttributeString
import com.mishaki.galgamehelper.html.dsl.generateCloseTagCode
import com.mishaki.galgamehelper.html.dsl.generateValueAndAttributeCode
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyIdAttribute
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodyGroup
import com.mishaki.galgamehelper.html.util.bodyListToString

class HtmlBodyDivTag: HtmlBodyGroup<HtmlBody>, HtmlBodyIdAttribute {
    private val bodyList: MutableList<HtmlBody> = ArrayList()
    override var id: String? = null

    override fun getTagString(): String = "div"

    override fun toHtmlCode(): String {
        val attribute = generateAttributeString(
            getIdAttribute()
        )
        return if (bodyList.isEmpty()) {
            generateCloseTagCode {attribute}
        } else {
            generateValueAndAttributeCode {
                attributeString = attribute
                valueString = bodyList.bodyListToString()
            }
        }
    }

    override fun getBodyList(): MutableList<HtmlBody> = bodyList
}
