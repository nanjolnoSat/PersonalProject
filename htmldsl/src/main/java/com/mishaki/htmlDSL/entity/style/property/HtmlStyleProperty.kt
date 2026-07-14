package com.mishaki.htmlDSL.entity.style.property

import com.mishaki.htmlDSL.entity.style.property.value.size.HtmlBoxSize
import com.mishaki.htmlDSL.entity.style.property.value.HtmlColor
import com.mishaki.htmlDSL.entity.style.property.value.HtmlDisplay
import com.mishaki.htmlDSL.entity.style.property.value.font.HtmlFontFamily
import com.mishaki.htmlDSL.entity.style.property.value.font.HtmlFontWeight
import com.mishaki.htmlDSL.entity.style.property.value.size.HtmlFontSize
import com.mishaki.htmlDSL.entity.style.property.value.size.HtmlLineHeight
import com.mishaki.htmlDSL.entity.style.property.value.size.HtmlSize
import com.mishaki.htmlDSL.entity.style.property.value.size.HtmlMargin
import com.mishaki.htmlDSL.entity.style.property.value.size.HtmlPadding

// TODO:
// margin / margin-top、padding / padding-top 等存在覆盖关系。
// 理想情况下，toString() 应根据赋值顺序调整输出顺序，以保持与 Kotlin 赋值顺序一致。
// 当前采用固定顺序输出，功能正常，后续统一优化。
class HtmlStyleProperty {
    // region Size
    var width: HtmlBoxSize? = null
    var height: HtmlBoxSize? = null
    var minWidth: HtmlBoxSize? = null
    var minHeight: HtmlBoxSize? = null
    var maxWidth: HtmlBoxSize? = null
    var maxHeight: HtmlBoxSize? = null
    // endregion

    // region padding
    var padding: HtmlPadding? = null
    var paddingLeft: HtmlSize? = null
    var paddingTop: HtmlSize? = null
    var paddingRight: HtmlSize? = null
    var paddingBottom: HtmlSize? = null
    // endregion

    // region margin
    var margin: HtmlMargin? = null
    var marginLeft: HtmlSize? = null
    var marginTop: HtmlSize? = null
    var marginRight: HtmlSize? = null
    var marginBottom: HtmlSize? = null
    // endregion

    var color: HtmlColor? = null
    var backgroundColor: HtmlColor? = null
    var display: HtmlDisplay? = null

    // region font
    var fontSize: HtmlFontSize? = null
    var fontWeight: HtmlFontWeight? = null
    var lineHeight: HtmlLineHeight? = null
    var fontFamily: HtmlFontFamily? = null
    var fontVariant: String? = null
    var font: String? = null
    var textIndent: HtmlSize? = null
    // endregion

    override fun toString(): String {
        return getCssList().joinToString("")
    }

    fun getCssList(): List<String> {
        return listOfNotNull(
            // region Size
            width.css("width") { it.toString() },
            height.css("height") { it.toString() },
            minWidth.css("min-width") { it.toString() },
            minHeight.css("min-height") { it.toString() },
            maxWidth.css("max-width") { it.toString() },
            maxHeight.css("max-height") { it.toString() },
            // endregion

            // region padding
            padding.css("padding") { it.value },
            paddingLeft.css("padding-left") { it.value },
            paddingTop.css("padding-top") { it.value },
            paddingRight.css("padding-right") { it.value },
            paddingBottom.css("padding-bottom") { it.value },
            // endregion

            // region marign
            margin.css("margin") { it.value },
            marginLeft.css("margin-left") { it.value },
            marginTop.css("margin-top") { it.value },
            marginRight.css("margin-right") { it.value },
            marginBottom.css("margin-bottom") { it.value },
            // endregion

            color.css("color") { it.value },
            backgroundColor.css("background-color") { it.value },
            display.css("display") { it.value },

            // region font
            fontSize.css("font-size") { it.toString() },
            fontWeight.css("font-weight") { it.value },
            lineHeight.css("line-height") { it.toString() },
            fontFamily.css("font-family") { it.toString() },
            fontVariant.css("font-variant") { it },
            font.css("font") { it },
            textIndent.css("text-indent") { it.value },
            // endregion
        )
    }

    private fun <T> T?.css(name: String, transform: (T) -> String): String? {
        return this?.let {
            "$name:${transform(it)};"
        }
    }
}