package com.mishaki.galgamehelper.html.entity.base

interface HtmlTag {
    fun getTagString(): String

    fun toHtmlCode(): String
}