package com.mishaki.htmlDSL.entity.style.property.value.size

@JvmInline
value class HtmlRatio(val value: String): HtmlLineHeight {
    override fun toString(): String = value
}

val Int.ratio: HtmlRatio
    get() = HtmlRatio(this.toString())

val Float.ratio: HtmlRatio
    get() = HtmlRatio(this.toString())

val Double.ratio: HtmlRatio
    get() = HtmlRatio(this.toString())