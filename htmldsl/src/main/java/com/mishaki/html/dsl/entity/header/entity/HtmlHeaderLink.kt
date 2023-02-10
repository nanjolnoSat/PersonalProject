package com.mishaki.galgamehelper.html.entity.header.entity

import com.mishaki.galgamehelper.html.util.generateHtmlCode
import com.mishaki.galgamehelper.html.entity.header.attribute.HtmlHeaderAttribute
import com.mishaki.galgamehelper.html.entity.header.attribute.HtmlHeaderGeneralAttribute
import com.mishaki.galgamehelper.html.entity.header.attribute.HtmlHeaderGeneralAttributeEntity
import com.mishaki.galgamehelper.html.entity.header.base.HtmlHeader
import com.mishaki.galgamehelper.html.util.toPairByStringValue

class HtmlHeaderLink: HtmlHeader, HtmlHeaderLinkAttribute {
    override val attributeEntity: HtmlHeaderLinkAttributeEntity = HtmlHeaderLinkAttributeEntity()
    override fun getTagString(): String = TAG

    override fun toHtmlCode(): String {
        return generateHtmlCode()
    }

    override fun getAttributeList(): List<Pair<String, Any>> {
        return super.getAttributeList()
    }

    companion object{
        const val TAG = "link"
    }
}

interface HtmlHeaderLinkAttribute: HtmlHeaderGeneralAttribute<HtmlHeaderLinkAttributeEntity> {
    var rel: String?
        get() = attributeEntity.rel
        set(value) {
            attributeEntity.rel = value
        }

    var href: String?
        get() = attributeEntity.href
        set(value) {
            attributeEntity.href = value
        }

    override fun getAttributeList(): List<Pair<String, Any>> {
        val currentList = listOfNotNull(
            HtmlHeaderAttribute.link.REL toPairByStringValue attributeEntity.rel,
            HtmlHeaderAttribute.link.HREF toPairByStringValue attributeEntity.href,
        )
        val superList = super.getAttributeList()
        return listOf(superList, currentList).flatten()
    }
}

class HtmlHeaderLinkAttributeEntity: HtmlHeaderGeneralAttributeEntity() {
    var rel: String? = null
    var href: String? = null
}