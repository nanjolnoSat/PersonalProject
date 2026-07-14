package com.mishaki.htmlDSL.entity.style.property.value.font

import com.mishaki.htmlDSL.entity.style.property.value.size.HtmlLineHeight

@JvmInline
value class HtmlLineHeightKeyword(val value: String): HtmlLineHeight {
    override fun toString(): String = value

    companion object {
        val Normal = HtmlLineHeightKeyword("normal")
    }
}