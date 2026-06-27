package com.mishaki.htmlDSL.entity.style.property.value

@JvmInline
value class HtmlSpacing(val value: String) {
    companion object {
        fun of(all: HtmlSize): HtmlSpacing =
            HtmlSpacing(all.value)

        fun of(vertical: HtmlSize, horizontal: HtmlSize): HtmlSpacing
            = HtmlSpacing("${vertical.value} ${horizontal.value}")

        fun of(top: HtmlSize, horizontal: HtmlSize, bottom: HtmlSize): HtmlSpacing
            = HtmlSpacing("${top.value} ${horizontal.value} ${bottom.value}")

        fun of(top: HtmlSize, right: HtmlSize, bottom: HtmlSize, left: HtmlSize): HtmlSpacing
            = HtmlSpacing("${top.value} ${right.value} ${bottom.value} ${left.value}")
    }
}

val String.spacing
    get() = HtmlSpacing(this)