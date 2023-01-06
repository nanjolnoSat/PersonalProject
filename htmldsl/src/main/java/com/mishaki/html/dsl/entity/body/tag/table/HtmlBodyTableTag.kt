package com.mishaki.galgamehelper.html.entity.body.tag.table

import com.mishaki.galgamehelper.html.dsl.generateAttributeString
import com.mishaki.galgamehelper.html.dsl.generateValueAndAttributeCode
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyAttribute
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyIdAttribute

class HtmlBodyTableTag: HtmlBody, HtmlBodyIdAttribute {
    override var id: String? = null
    var border: Int = 10
    var cellpadding: Int = 0
    var cellspacing: Int = 0
    var thead: HtmlBodyTableTHead? = null
    var tbody: HtmlBodyTableTBodyTag? = null
    var tfoot: HtmlBodyTableTFootTag? = null

    override fun getTagString(): String = "table"

    override fun toHtmlCode(): String {
        return generateValueAndAttributeCode {
            attributeString = generateAttributeString(
                getIdAttribute(),
                HtmlBodyAttribute.BORDER to border,
                HtmlBodyAttribute.CELL_PADDING to cellpadding,
                HtmlBodyAttribute.CELL_SPACING to cellspacing,
            )
            setValueWithStringBuilder {
                thead?.toHtmlCode()?.let(::append)
                tbody?.toHtmlCode()?.let(::append)
                tfoot?.toHtmlCode()?.let(::append)
            }
        }
    }
}
