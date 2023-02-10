package com.mishaki.galgamehelper.html.entity.body.tag.general

import com.mishaki.galgamehelper.html.util.generateHtmlCode
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyAttribute
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyGeneralAttribute
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyGeneralAttributeEntity
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodySingle
import com.mishaki.galgamehelper.html.util.toPairByStringValue

class HtmlBodyImgTag: HtmlBodySingle<HtmlBody>(), HtmlBodyImgAttribute {
    override val attributeEntity: HtmlBodyImgAttributeEntity = HtmlBodyImgAttributeEntity()
    override fun getTagString(): String = TAG

    override fun toHtmlCode(): String {
        return generateHtmlCode()
    }

    override fun getAttributeList(): List<Pair<String, Any>> {
        return super.getAttributeList()
    }

    companion object{
        const val TAG = "img"
    }
}

interface HtmlBodyImgAttribute: HtmlBodyGeneralAttribute<HtmlBodyImgAttributeEntity> {
    var src: String?
        get() = attributeEntity.src
        set(value) {
            attributeEntity.src = value
        }

    override fun getAttributeList(): List<Pair<String, Any>> {
        val superList = super.getAttributeList()
        val currentList = listOfNotNull(
            HtmlBodyAttribute.img.SRC toPairByStringValue attributeEntity.src,
        )
        return listOf(superList, currentList).flatten()
    }
}

class HtmlBodyImgAttributeEntity: HtmlBodyGeneralAttributeEntity() {
    var src: String? = null
}