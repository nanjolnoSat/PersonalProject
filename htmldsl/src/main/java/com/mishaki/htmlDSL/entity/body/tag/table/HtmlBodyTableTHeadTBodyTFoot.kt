package com.mishaki.htmlDSL.entity.body.tag.table

import com.mishaki.htmlDSL.util.generateHtmlCode
import com.mishaki.htmlDSL.entity.body.attribute.HtmlBodyGeneralAttribute
import com.mishaki.htmlDSL.entity.body.attribute.HtmlBodyGeneralAttributeEntity
import com.mishaki.htmlDSL.entity.body.base.HtmlBodyGroup

abstract class HtmlBodyTableTHeadTBodyTFoot : HtmlBodyGroup<HtmlBodyTableTrTag>(),
    HtmlBodyTHeadTBodyAttribute {
    override val attributeEntity: HtmlBodyGeneralAttributeEntity = HtmlBodyGeneralAttributeEntity()

    override fun getAttributeList(): List<Pair<String, Any>> {
        return super.getAttributeList()
    }

    override fun toHtmlCode(): String {
        return generateHtmlCode()
    }
}

interface HtmlBodyTHeadTBodyAttribute: HtmlBodyGeneralAttribute<HtmlBodyGeneralAttributeEntity>