package com.mishaki.htmlDSL.entity.style.property.value.size

@JvmInline
value class HtmlMargin(val value: String) {
    companion object {
        val Auto = HtmlMargin("auto")

        fun autoHorizontal(size: HtmlSize) = HtmlMargin("auto ${size.value}")

        fun autoVertical(size: HtmlSize) = HtmlMargin("${size.value} auto")

        fun of(all: HtmlSize): HtmlMargin =
            HtmlMargin(all.value)

        fun of(vertical: HtmlSize, horizontal: HtmlSize): HtmlMargin
            = HtmlMargin("${vertical.value} ${horizontal.value}")

        fun of(top: HtmlSize, horizontal: HtmlSize, bottom: HtmlSize): HtmlMargin
            = HtmlMargin("${top.value} ${horizontal.value} ${bottom.value}")

        fun of(top: HtmlSize, right: HtmlSize, bottom: HtmlSize, left: HtmlSize): HtmlMargin
            = HtmlMargin("${top.value} ${right.value} ${bottom.value} ${left.value}")
    }
}