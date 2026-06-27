package com.mishaki.htmlDSL.entity.body.tag.table

import com.mishaki.htmlDSL.util.generateHtmlCode
import com.mishaki.htmlDSL.entity.body.attribute.HtmlBodyAttribute
import com.mishaki.htmlDSL.entity.body.attribute.HtmlBodyGeneralAttribute
import com.mishaki.htmlDSL.entity.body.attribute.HtmlBodyGeneralAttributeEntity
import com.mishaki.htmlDSL.entity.body.base.HtmlBody
import com.mishaki.htmlDSL.entity.body.base.HtmlBodyGroup
import com.mishaki.htmlDSL.util.append

abstract class HtmlBodyTableThTdTag: HtmlBodyGroup<HtmlBody>(), HtmlBodyTableThTdAttribute {
    override val attributeEntity: HtmlBodyTableThTdAttributeEntity =
        HtmlBodyTableThTdAttributeEntity()

    override fun getAttributeList(): List<Pair<String, Any>> {
        return super.getAttributeList()
    }

    override fun toHtmlCode(): String {
        return generateHtmlCode()
    }
}


interface HtmlBodyTableThTdAttribute: HtmlBodyGeneralAttribute<HtmlBodyTableThTdAttributeEntity> {
    var colspan: Int
        get() = attributeEntity.colspan
        set(value) {
            attributeEntity.colspan = value
        }

    override fun getAttributeList(): List<Pair<String, Any>> {
        return super.getAttributeList().append(
            HtmlBodyAttribute.table.COL_SPAN to attributeEntity.colspan,
        )
    }
}

class HtmlBodyTableThTdAttributeEntity: HtmlBodyGeneralAttributeEntity() {
    var colspan: Int = 1
}
