package com.mishaki.galgamehelper.html.entity.body.attribute

interface HtmlBodyIdAttribute {
    var id: String?

    fun getIdAttribute(): Pair<String, String>? {
        return id?.takeIf {it.isNotEmpty()}?.let {HtmlBodyAttribute.ID to it}
    }
}
