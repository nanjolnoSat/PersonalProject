package com.mishaki.htmlDSL.entity.style

import com.mishaki.htmlDSL.entity.base.HtmlStyle

class HtmlStyleRoot: HtmlStyle {
    private val styleList = ArrayList<String>()

    fun addStyleCode(code: String) {
        styleList.add(code)
    }

    fun removeStyleCode(code: String) {
        styleList.remove(code)
    }

    fun clearStyleCode() {
        styleList.clear()
    }

    override fun toStyleCode(): String {
        return if (styleList.isNotEmpty()) {
            "<style>${styleList.joinToString("\n")}</style>"
        } else {
            return "<style/>"
        }
    }
}
