package com.mishaki.galgamehelper.html.entity.body.attribute

import com.mishaki.galgamehelper.html.util.toPairByStringValue

interface HtmlBodyGeneralAttribute<T: HtmlBodyGeneralAttributeEntity> {
    val attributeEntity: T

    var id: String?
        get() = attributeEntity.id
        set(value) {
            attributeEntity.id = value
        }

    var width: String?
        get() = attributeEntity.width
        set(value) {
            attributeEntity.width = value
        }

    var height: String?
        get() = attributeEntity.height
        set(value) {
            attributeEntity.height = value
        }

    fun getAttributeList(): List<Pair<String, Any>> {
        return listOfNotNull(
            HtmlBodyAttribute.general.ID toPairByStringValue attributeEntity.id,
            HtmlBodyAttribute.general.WIDTH toPairByStringValue attributeEntity.width,
            HtmlBodyAttribute.general.HEIGHT toPairByStringValue attributeEntity.height,
        )
    }
}


open class HtmlBodyGeneralAttributeEntity {
    var id: String? = null
    var width: String? = null
    var height: String? = null
}

interface HtmlBodyGeneralAttributeDefaultImpl: HtmlBodyGeneralAttribute<HtmlBodyGeneralAttributeEntity>