package com.mishaki.galgamehelper.html.entity.body.tag.general

import com.mishaki.galgamehelper.html.util.generateHtmlCode
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyAttribute
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyGeneralAttribute
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyGeneralAttributeEntity
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodySingle
import com.mishaki.galgamehelper.html.util.toPairByStringValue

class HtmlBodyATag: HtmlBodySingle<HtmlBody>(), HtmlBodyAAttribute {
    override val attributeEntity: HtmlBodyAAttributeEntity = HtmlBodyAAttributeEntity()

    override fun getTagString(): String = TAG

    override fun toHtmlCode(): String {
        return generateHtmlCode()
    }

    override fun getAttributeList(): List<Pair<String, Any>> {
        return super.getAttributeList()
    }

    companion object{
        const val TAG = "a"
    }
}

interface HtmlBodyAAttribute: HtmlBodyGeneralAttribute<HtmlBodyAAttributeEntity> {
    var href: String?
        get() = attributeEntity.href
        set(value) {
            attributeEntity.href = value
        }

    var target: String?
        get() = attributeEntity.target
        set(value) {
            attributeEntity.target = value
        }

    override fun getAttributeList(): List<Pair<String, Any>> {
        val superList = super.getAttributeList()
        val currentList = listOfNotNull(
            HtmlBodyAttribute.a.HREF toPairByStringValue attributeEntity.href,
            HtmlBodyAttribute.a.TARGET toPairByStringValue attributeEntity.target,
        )
        return listOf(superList, currentList).flatten()
    }
}

class HtmlBodyAAttributeEntity: HtmlBodyGeneralAttributeEntity() {
    var href: String? = null
    var target: String? = null
}
