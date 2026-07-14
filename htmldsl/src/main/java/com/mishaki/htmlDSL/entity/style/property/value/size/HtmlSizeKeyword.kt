package com.mishaki.htmlDSL.entity.style.property.value.size

@JvmInline
value class HtmlSizeKeyword(val value: String): HtmlBoxSize {
    override fun toString(): String = value

    companion object {
        val Auto = HtmlSizeKeyword("auto")
        val MinContent = HtmlSizeKeyword("min-content")
        val MaxContent = HtmlSizeKeyword("max-content")
        val FitContent = HtmlSizeKeyword("fit-content")
        val Stretch = HtmlSizeKeyword("stretch")
    }
}