package com.mishaki.htmlDSL.entity.style.property

import com.mishaki.htmlDSL.entity.style.property.value.HtmlColor
import com.mishaki.htmlDSL.entity.style.property.value.HtmlDisplay
import com.mishaki.htmlDSL.entity.style.property.value.HtmlSize
import com.mishaki.htmlDSL.entity.style.property.value.HtmlSpacing

// TODO:
// margin / margin-top、padding / padding-top 等存在覆盖关系。
// 理想情况下，toString() 应根据赋值顺序调整输出顺序，以保持与 Kotlin 赋值顺序一致。
// 当前采用固定顺序输出，功能正常，后续统一优化。
class HtmlStyleProperty {
    // region Size
    var width: HtmlSize? = null
    var height: HtmlSize? = null
    var minWidth: HtmlSize? = null
    var minHeight: HtmlSize? = null
    var maxWidth: HtmlSize? = null
    var maxHeight: HtmlSize? = null
    // endregion

    // region padding
    var padding: HtmlSpacing? = null
    var paddingLeft: HtmlSize? = null
    var paddingTop: HtmlSize? = null
    var paddingRight: HtmlSize? = null
    var paddingBottom: HtmlSize? = null
    // endregion

    // region margin
    var margin: HtmlSpacing? = null
    var marginLeft: HtmlSize? = null
    var marginTop: HtmlSize? = null
    var marginRight: HtmlSize? = null
    var marginBottom: HtmlSize? = null
    // endregion

    var color: HtmlColor? = null
    var display: HtmlDisplay? = null

    override fun toString(): String {
        return getCssList().joinToString("")
    }

    fun getCssList(): List<String> {
        return listOfNotNull(
            // region Size
            width.css("width") { it.value },
            height.css("height") { it.value },
            minWidth.css("min-width") { it.value },
            minHeight.css("min-height") { it.value },
            maxWidth.css("max-width") { it.value },
            maxHeight.css("max-height") { it.value },
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
            display.css("display") { it.value },
        )
    }

    private fun <T> T?.css(name: String, transform: (T) -> String): String? {
        return this?.let {
            "$name:${transform(it)};"
        }
    }
}