package com.mishaki.galgamehelper.html.entity.body.attribute

interface HtmlBodyWidthHeightAttribute {
    var width: String?
    var height: String?

    fun getWidthAttribute(): Pair<String, String>? {
        return width?.takeIf {it.isNotEmpty()}?.let {HtmlBodyAttribute.WIDTH to it}
    }

    fun getHeightAttribute(): Pair<String, String>? {
        return height?.takeIf {it.isNotEmpty()}?.let {HtmlBodyAttribute.HEIGHT to it}
    }
}