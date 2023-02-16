package com.mishaki.galgamehelper.html.entity.header.attribute

interface HtmlHeaderGeneralAttribute<T: HtmlHeaderGeneralAttributeEntity> {
    val attributeEntity: T

    fun getAttributeList(): List<Pair<String, Any>> = emptyList()
}

open class HtmlHeaderGeneralAttributeEntity

interface HtmlHeaderGeneralAttributeDefaultImpl: HtmlHeaderGeneralAttribute<HtmlHeaderGeneralAttributeEntity>