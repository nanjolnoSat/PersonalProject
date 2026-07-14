package com.mishaki.htmlDSL.entity.style.property.value.font

import com.mishaki.htmlDSL.entity.style.property.value.size.HtmlFontSize

@JvmInline
value class HtmlFontSizeKeyword(val value: String): HtmlFontSize {
    override fun toString(): String = value

    companion object {
        val XXSmall = HtmlFontSizeKeyword("xx-small")
        val XSmall = HtmlFontSizeKeyword("x-small")
        val Small = HtmlFontSizeKeyword("small")
        val Medium = HtmlFontSizeKeyword("medium")
        val Large = HtmlFontSizeKeyword("large")
        val XLarge = HtmlFontSizeKeyword("x-large")
        val XXLarge = HtmlFontSizeKeyword("xx-large")
        val Smaller = HtmlFontSizeKeyword("smaller")
        val Larger = HtmlFontSizeKeyword("larger")
    }
}