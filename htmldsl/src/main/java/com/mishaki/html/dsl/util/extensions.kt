package com.mishaki.galgamehelper.html.util

import com.mishaki.galgamehelper.html.entity.HtmlRoot
import com.mishaki.galgamehelper.html.entity.base.HtmlTag
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodyGroup
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodySingle
import com.mishaki.galgamehelper.html.entity.body.tag.general.HtmlBodyTextTag
import com.mishaki.galgamehelper.html.entity.header.base.HtmlHeader
import com.mishaki.galgamehelper.html.entity.header.base.HtmlHeaderGroup

fun <T: HtmlTag> List<T>.tagListToString() = joinToString("") { it.toHtmlCode() }

fun <T: HtmlHeader> List<T>.headerTagListToString() = joinToString("") { it.toHtmlCode() }

fun <T: HtmlBody> List<T>.bodyTagListToString() = joinToString("") { it.toHtmlCode() }

fun String.repeat(times: Int): String {
    return buildString {
        repeat(times) {
            append(this@repeat)
        }
    }
}

infix fun String.toPairByStringValue(value: String?): Pair<String, String>? {
    return value?.takeIf { it.isNotEmpty() }?.let { this to value }
}

fun HtmlTag.generateHtmlCode(): String {
    return generateHtmlCode("")
}

fun <T: HtmlBody> HtmlBodySingle<T>.generateHtmlCode(): String {
    return generateHtmlCode(body?.toHtmlCode() ?: "")
}

fun <T: HtmlBody> HtmlBodyGroup<T>.generateHtmlCode(): String {
    return generateHtmlCode(getBodyList().bodyTagListToString())
}

fun <T: HtmlHeader> HtmlHeaderGroup<T>.generateHtmlCode(): String {
    return generateHtmlCode(getHeaderList().headerTagListToString())
}

fun <T: HtmlTag> HtmlTag.generateHtmlCode(htmlList: List<T>): String {
    return generateHtmlCode(htmlList.tagListToString())

}

fun HtmlTag.generateHtmlCodeByStringList(codeList: List<String>): String {
    return generateHtmlCode(codeList.joinToString(""))
}

fun HtmlTag.generateHtmlCode(value: String): String {
    val attributeString = getAttributeList().takeIf { it.isNotEmpty() }?.joinToString(" ") {
        "${it.first}=\"${it.second}\""
    }?.let { " $it" } ?: ""
    return if(value.isEmpty()) {
        "<${getTagString()}$attributeString/>"
    } else {
        buildString {
            append("<${getTagString()}$attributeString>")
            append(value)
            append("</${getTagString()}>")
        }
    }
}

fun HtmlRoot.getTextLength(): Int {
    return body?.getTextBodyList()?.sumOf {
        it.text.length
    } ?: 0
}

fun HtmlBody.getTextBodyList(): List<HtmlBodyTextTag> {
    return when(this) {
        is HtmlBodyTextTag -> {
            arrayListOf(this)
        }
        is HtmlBodySingle<*> -> {
            getTextBodyList()
        }
        is HtmlBodyGroup<*> -> {
            getTextBodyList()
        }
        else -> emptyList()
    }
}

fun HtmlBodyGroup<*>.getTextBodyList(): List<HtmlBodyTextTag> {
    val list = ArrayList<HtmlBodyTextTag>()
    getBodyList().forEach {
        it.getTextBodyList().takeIf{ it.isNotEmpty() }?.also(list::addAll)
    }
    return list
}

fun HtmlBodySingle<*>.getTextBodyList(): List<HtmlBodyTextTag> {
    return body?.getTextBodyList() ?: emptyList()
}
