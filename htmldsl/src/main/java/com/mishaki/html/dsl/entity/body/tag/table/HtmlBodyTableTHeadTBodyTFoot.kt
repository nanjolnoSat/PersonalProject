package com.mishaki.galgamehelper.html.entity.body.tag.table

import com.mishaki.galgamehelper.html.util.generateHtmlCode
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyGeneralAttribute
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyGeneralAttributeEntity
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodyGroup

abstract class HtmlBodyTableTHeadTBodyTFoot :HtmlBodyGroup<HtmlBodyTableTrTag>(), HtmlBodyTHeadTBodyAttribute{
    override val attributeEntity: HtmlBodyGeneralAttributeEntity = HtmlBodyGeneralAttributeEntity()

    override fun getAttributeList(): List<Pair<String, Any>> {
        return super.getAttributeList()
    }

    override fun toHtmlCode(): String {
        return generateHtmlCode()
    }
}

interface HtmlBodyTHeadTBodyAttribute: HtmlBodyGeneralAttribute<HtmlBodyGeneralAttributeEntity>