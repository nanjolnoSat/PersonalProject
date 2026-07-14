package com.mishaki.htmlDSL.entity.style.property.value.size

@JvmInline
value class HtmlPadding(val value: String) {
    companion object {
        fun of(all: HtmlSize): HtmlPadding = HtmlPadding(all.value)

        fun of(vertical: HtmlSize, horizontal: HtmlSize): HtmlPadding
            = HtmlPadding("${vertical.value} ${horizontal.value}")

        fun of(top: HtmlSize, horizontal: HtmlSize, bottom: HtmlSize): HtmlPadding
            = HtmlPadding("${top.value} ${horizontal.value} ${bottom.value}")

        fun of(top: HtmlSize, right: HtmlSize, bottom: HtmlSize, left: HtmlSize): HtmlPadding
            = HtmlPadding("${top.value} ${right.value} ${bottom.value} ${left.value}")
    }
}