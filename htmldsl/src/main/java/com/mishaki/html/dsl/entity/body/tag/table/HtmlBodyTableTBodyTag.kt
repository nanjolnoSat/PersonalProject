package com.mishaki.galgamehelper.html.entity.body.tag.table

import com.mishaki.galgamehelper.html.dsl.generateAttributeString
import com.mishaki.galgamehelper.html.dsl.generateValueAndAttributeCode
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyIdAttribute
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodyGroup
import com.mishaki.galgamehelper.html.util.bodyListToString

class HtmlBodyTableTBodyTag: HtmlBodyGroup<HtmlBodyTableTrTag>, HtmlBodyIdAttribute {
    private val bodyList: MutableList<HtmlBodyTableTrTag> = ArrayList()
    override var id: String? = null

    override fun getTagString(): String = "tbody"

    override fun toHtmlCode(): String {
        return generateValueAndAttributeCode {
            attributeString = generateAttributeString(
                getIdAttribute()
            )
            valueString = bodyList.bodyListToString()
        }
    }

    override fun getBodyList(): MutableList<HtmlBodyTableTrTag> = bodyList
}
