package com.mishaki.htmlDSL.entity.style.property.value.font

@JvmInline
value class HtmlFontWeight(val value: String) {
    companion object {
        fun of(value: Int) = HtmlFontWeight(value.toString())

        val Normal = HtmlFontWeight("normal")
        val Bold = HtmlFontWeight("bold");
        val Bolder = HtmlFontWeight("bolder")
        val Lighter = HtmlFontWeight("lighter")
    }
}

val Int.fontWeight: HtmlFontWeight
    get() = HtmlFontWeight.of(this)

