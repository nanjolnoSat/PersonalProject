package com.mishaki.htmlDSL.entity.style.property.value.font

@JvmInline
value class HtmlFontStyle(val value: String) {
    companion object {
        val Normal = HtmlFontStyle("normal")
        val Italic = HtmlFontStyle("italic")
        val Oblique = HtmlFontStyle("oblique")
    }
}