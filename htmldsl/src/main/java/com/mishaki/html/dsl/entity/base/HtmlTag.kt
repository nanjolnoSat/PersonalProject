package com.mishaki.galgamehelper.html.entity.base

interface HtmlTag {
    fun getTagString(): String

    fun getAttributeList(): List<Pair<String, Any>>

    fun toHtmlCode(): String
}