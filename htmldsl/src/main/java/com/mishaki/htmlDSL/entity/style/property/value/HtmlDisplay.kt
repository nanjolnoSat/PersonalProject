package com.mishaki.htmlDSL.entity.style.property.value

@JvmInline
value class HtmlDisplay private constructor(val value: String) {
    companion object {
        val none: HtmlDisplay
            get() = HtmlDisplay("none")

        val block: HtmlDisplay
            get() = HtmlDisplay("block")

        val inlineBlock: HtmlDisplay
            get() = HtmlDisplay("inline-block")

        val inline: HtmlDisplay
            get() = HtmlDisplay("inline")

        val flex: HtmlDisplay
            get() = HtmlDisplay("flex")

        val table: HtmlDisplay
            get() = HtmlDisplay("table")
    }
}