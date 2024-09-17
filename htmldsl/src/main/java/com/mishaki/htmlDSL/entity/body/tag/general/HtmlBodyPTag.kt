package com.mishaki.htmlDSL.entity.body.tag.general

import com.mishaki.htmlDSL.util.generateHtmlCode
import com.mishaki.htmlDSL.entity.body.attribute.HtmlBodyGeneralAttributeDefaultImpl
import com.mishaki.htmlDSL.entity.body.attribute.HtmlBodyGeneralAttributeEntity
import com.mishaki.htmlDSL.entity.body.base.HtmlBodyTextBase

class HtmlBodyPTag: HtmlBodyTextBase(), HtmlBodyGeneralAttributeDefaultImpl {
    override val attributeEntity: HtmlBodyGeneralAttributeEntity = HtmlBodyGeneralAttributeEntity()
    override fun getTagString(): String = TAG

    override fun toHtmlCode(): String {
        return generateHtmlCode()
    }

    override fun getAttributeList(): List<Pair<String, Any>> {
        return super.getAttributeList()
    }

    companion object{
        const val TAG = "p"
    }
}
