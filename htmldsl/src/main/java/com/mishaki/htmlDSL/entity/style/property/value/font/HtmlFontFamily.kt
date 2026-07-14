package com.mishaki.htmlDSL.entity.style.property.value.font

@JvmInline
value class HtmlFontFamily(val value: String) {
    companion object {
        val Serif = HtmlFontFamily("serif")
        val SansSerif = HtmlFontFamily("sans-serif")
        val Monospace = HtmlFontFamily("monospace")
        val Cursive = HtmlFontFamily("cursive")
        val Fantasy = HtmlFontFamily("fantasy")

        fun of(vararg value: String): HtmlFontFamily {
            return HtmlFontFamily(value.joinToString(", "))
        }
    }
}

val String.fontFamily: HtmlFontFamily
    get() = HtmlFontFamily(this)