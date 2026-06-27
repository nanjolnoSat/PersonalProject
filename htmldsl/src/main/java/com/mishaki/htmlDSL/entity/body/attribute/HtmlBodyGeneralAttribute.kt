package com.mishaki.htmlDSL.entity.body.attribute

import com.mishaki.htmlDSL.entity.style.property.HtmlStyleProperty
import com.mishaki.htmlDSL.util.toPairByStringValue
import com.mishaki.htmlDSL.util.toPairUseToString

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

    var style: HtmlStyleProperty?
        get() = attributeEntity.style
        set(value) {
            attributeEntity.style = value
        }

    fun style(action: HtmlStyleProperty.() -> Unit) {
        val style = style ?: HtmlStyleProperty().also {
            style = it
        }
        style.action()
    }

    fun getAttributeList(): List<Pair<String, Any>> {
        return listOfNotNull(
            HtmlBodyAttribute.general.ID toPairByStringValue attributeEntity.id,
            HtmlBodyAttribute.general.WIDTH toPairByStringValue attributeEntity.width,
            HtmlBodyAttribute.general.HEIGHT toPairByStringValue attributeEntity.height,
            HtmlBodyAttribute.general.STYLE toPairUseToString attributeEntity.style,
        )
    }
}


open class HtmlBodyGeneralAttributeEntity {
    var id: String? = null
    var width: String? = null
    var height: String? = null
    var style: HtmlStyleProperty? = null
}

interface HtmlBodyGeneralAttributeDefaultImpl: HtmlBodyGeneralAttribute<HtmlBodyGeneralAttributeEntity>