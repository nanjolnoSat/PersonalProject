package com.mishaki.htmlDSL.entity.style.property

import com.mishaki.htmlDSL.entity.style.property.value.HtmlColor
import com.mishaki.htmlDSL.entity.style.property.value.HtmlSize

class HtmlStyleProperty {
    var width: HtmlSize? = null
    var height: HtmlSize? = null
    var color: HtmlColor? = null

    override fun toString(): String {
        return getCssList().joinToString("")
    }

    fun getCssList(): List<String> {
        return listOfNotNull(
            width.css("width") { it.value },
            height.css("height") { it.value },
            color.css("color") { it.value },
        )
    }

    private fun <T> T?.css(name: String, transform: (T) -> String): String? {
        return this?.let {
            "$name:${transform(it)};"
        }
    }
}