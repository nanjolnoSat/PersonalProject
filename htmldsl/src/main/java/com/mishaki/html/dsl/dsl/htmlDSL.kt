package com.mishaki.galgamehelper.html.dsl

import com.mishaki.galgamehelper.html.entity.body.HtmlBodyRoot
import com.mishaki.galgamehelper.html.entity.HtmlRoot
import com.mishaki.galgamehelper.html.entity.base.HtmlTag
import com.mishaki.galgamehelper.html.entity.header.HtmlHeaderRoot

inline fun html(action: HtmlRoot.() -> Unit): HtmlRoot {
    return HtmlRoot().also {
        it.action()
    }
}

inline fun HtmlRoot.header(headerAction: HtmlHeaderRoot.() -> Unit): HtmlHeaderRoot {
    return HtmlHeaderRoot().also {
        it.headerAction()
        header = it
    }
}

inline fun HtmlRoot.body(bodyAction: HtmlBodyRoot.() -> Unit): HtmlBodyRoot {
    return HtmlBodyRoot().also {
        it.bodyAction()
        body = it
    }
}

inline fun HtmlTag.generateValueCode(value: () -> String): String {
    return "<${getTagString()}>${value()}</${getTagString()}>"
}

inline fun HtmlTag.generateValueCodeWithStringBuilder(valueCodeAction: StringBuilder.() -> Unit): String {
    return "<${getTagString()}>${StringBuilder().also(valueCodeAction)}</${getTagString()}>"
}

inline fun HtmlTag.generateValueAndAttributeCode(action: HtmlValueAttributeHelper.() -> Unit): String {
    val helper = HtmlValueAttributeHelper().also(action)
    val attribute = helper.attributeString.takeIf {it.isNotEmpty()}?.let {" $it"} ?: ""
    return "<${getTagString()}$attribute>${helper.valueString}</${getTagString()}>"
}

inline fun HtmlTag.generateCloseTagCode(): String {
    return "<${getTagString()}/>"
}

inline fun HtmlTag.generateCloseTagCode(attributeCodeAction: () -> String): String {
    val attribute = attributeCodeAction().takeIf {it.isNotEmpty()}?.let {" $it"} ?: ""
    return "<${getTagString()}$attribute/>"
}

inline fun HtmlTag.generateCloseTagCodeWithStringBuilder(attributeCodeAction: StringBuilder.() -> Unit): String {
    val attribute =
        StringBuilder().also(attributeCodeAction).takeIf {it.isNotEmpty()}?.let {" $it"} ?: ""
    return "<${getTagString()}$attribute/>"
}

inline fun generateAttributeString(vararg attributeList: Pair<String, Any>?): String {
    return attributeList.filterNotNull().joinToString(" ") {"${it.first}=\"${it.second}\""}
}

class HtmlValueAttributeHelper {
    var valueString: String = ""
    var attributeString: String = ""

    fun setValueWithStringBuilder(valueStringCode: StringBuilder.() -> Unit) {
        this.valueString = StringBuilder().also(valueStringCode).toString()
    }

    fun setAttributeWithStringBuilder(attributeStringCode: StringBuilder.() -> Unit) {
        this.attributeString = StringBuilder().also(attributeStringCode).toString()
    }
}
