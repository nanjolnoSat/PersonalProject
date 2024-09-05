package com.mishaki.htmlDSL.entity.body.tag.table

import com.mishaki.htmlDSL.util.generateHtmlCode
import com.mishaki.htmlDSL.entity.body.attribute.HtmlBodyGeneralAttributeDefaultImpl
import com.mishaki.htmlDSL.entity.body.attribute.HtmlBodyGeneralAttributeEntity
import com.mishaki.htmlDSL.entity.body.base.HtmlBodyGroup

class HtmlBodyTableTrTag: HtmlBodyGroup<HtmlBodyTableThTdTag>(),
    HtmlBodyGeneralAttributeDefaultImpl {
    override val attributeEntity: HtmlBodyGeneralAttributeEntity = HtmlBodyGeneralAttributeEntity()
    override fun getTagString(): String = TAG

    override fun getAttributeList(): List<Pair<String, Any>> {
        return super.getAttributeList()
    }

    override fun toHtmlCode(): String {
        return generateHtmlCode()
    }

    companion object{
        const val TAG = "TR"
    }
}
