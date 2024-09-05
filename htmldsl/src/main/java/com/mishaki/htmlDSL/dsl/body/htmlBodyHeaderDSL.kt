package com.mishaki.htmlDSL.dsl.body

import com.mishaki.htmlDSL.entity.body.base.HtmlBody
import com.mishaki.htmlDSL.entity.body.base.HtmlBodyGroup
import com.mishaki.htmlDSL.entity.body.base.HtmlBodySingle
import com.mishaki.htmlDSL.entity.body.tag.header.HtmlBodyH1Tag
import com.mishaki.htmlDSL.entity.body.tag.header.HtmlBodyH2Tag
import com.mishaki.htmlDSL.entity.body.tag.header.HtmlBodyH3Tag
import com.mishaki.htmlDSL.entity.body.tag.header.HtmlBodyH4Tag
import com.mishaki.htmlDSL.entity.body.tag.header.HtmlBodyH5Tag
import com.mishaki.htmlDSL.entity.body.tag.header.HtmlBodyH6Tag
import com.mishaki.htmlDSL.entity.body.tag.header.HtmlBodyHTagBase

inline fun HtmlBodySingle<HtmlBody>.h1(text: String) {
    h1(text) {}
}

inline fun HtmlBodySingle<HtmlBody>.h1(action: HtmlBodyH1Tag.() -> Unit) {
    h1("", action)
}

inline fun HtmlBodySingle<HtmlBody>.h1(text: String, action: HtmlBodyH1Tag.() -> Unit) {
    h(getH1, text, action)
}

inline fun HtmlBodyGroup<HtmlBody>.h1(text: String): HtmlBodyGroup<HtmlBody> {
    return h1(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.h1(action: HtmlBodyH1Tag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    return h1("", action)
}

inline fun HtmlBodyGroup<HtmlBody>.h1(text: String, action: HtmlBodyH1Tag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    return h(getH1, text, action)
}

inline fun HtmlBodySingle<HtmlBody>.h2(text: String) {
    h2(text) {}
}

inline fun HtmlBodySingle<HtmlBody>.h2(action: HtmlBodyH2Tag.() -> Unit) {
    h2("", action)
}

inline fun HtmlBodySingle<HtmlBody>.h2(text: String, action: HtmlBodyH2Tag.() -> Unit) {
    h(getH2, text, action)
}

inline fun HtmlBodyGroup<HtmlBody>.h2(text: String): HtmlBodyGroup<HtmlBody> {
    return h2(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.h2(action: HtmlBodyH2Tag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    return h2("", action)
}

inline fun HtmlBodyGroup<HtmlBody>.h2(text: String, action: HtmlBodyH2Tag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    return h(getH2, text, action)
}

inline fun HtmlBodySingle<HtmlBody>.h3(text: String) {
    h3(text) {}
}

inline fun HtmlBodySingle<HtmlBody>.h3(action: HtmlBodyH3Tag.() -> Unit) {
    h3("", action)
}

inline fun HtmlBodySingle<HtmlBody>.h3(text: String, action: HtmlBodyH3Tag.() -> Unit) {
    h(getH3, text, action)
}

inline fun HtmlBodyGroup<HtmlBody>.h3(text: String): HtmlBodyGroup<HtmlBody> {
    return h3(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.h3(action: HtmlBodyH3Tag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    return h3("", action)
}

inline fun HtmlBodyGroup<HtmlBody>.h3(text: String, action: HtmlBodyH3Tag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    return h(getH3, text, action)
}

inline fun HtmlBodySingle<HtmlBody>.h4(text: String) {
    h4(text) {}
}

inline fun HtmlBodySingle<HtmlBody>.h4(action: HtmlBodyH4Tag.() -> Unit) {
    h4("", action)
}

inline fun HtmlBodySingle<HtmlBody>.h4(text: String, action: HtmlBodyH4Tag.() -> Unit) {
    h(getH4, text, action)
}

inline fun HtmlBodyGroup<HtmlBody>.h4(text: String): HtmlBodyGroup<HtmlBody> {
    return h4(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.h4(action: HtmlBodyH4Tag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    return h4("", action)
}

inline fun HtmlBodyGroup<HtmlBody>.h4(text: String, action: HtmlBodyH4Tag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    return h(getH4, text, action)
}

inline fun HtmlBodySingle<HtmlBody>.h5(text: String) {
    h5(text) {}
}

inline fun HtmlBodySingle<HtmlBody>.h5(action: HtmlBodyH5Tag.() -> Unit) {
    h5("", action)
}

inline fun HtmlBodySingle<HtmlBody>.h5(text: String, action: HtmlBodyH5Tag.() -> Unit) {
    h(getH5, text, action)
}

inline fun HtmlBodyGroup<HtmlBody>.h5(text: String): HtmlBodyGroup<HtmlBody> {
    return h5(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.h5(action: HtmlBodyH5Tag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    return h5("", action)
}

inline fun HtmlBodyGroup<HtmlBody>.h5(text: String, action: HtmlBodyH5Tag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    return h(getH5, text, action)
}

inline fun HtmlBodySingle<HtmlBody>.h6(text: String) {
    h6(text) {}
}

inline fun HtmlBodySingle<HtmlBody>.h6(action: HtmlBodyH6Tag.() -> Unit) {
    h6("", action)
}

inline fun HtmlBodySingle<HtmlBody>.h6(text: String, action: HtmlBodyH6Tag.() -> Unit) {
    h(getH6, text, action)
}

inline fun HtmlBodyGroup<HtmlBody>.h6(text: String): HtmlBodyGroup<HtmlBody> {
    return h6(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.h6(action: HtmlBodyH6Tag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    return h6("", action)
}

inline fun HtmlBodyGroup<HtmlBody>.h6(text: String, action: HtmlBodyH6Tag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    return h(getH6, text, action)
}

inline fun <T: HtmlBodyHTagBase> HtmlBodySingle<HtmlBody>.h(hTagInstance: T, text: String, action: T.() -> Unit) {
    hTagInstance.also {
        it.text = text
        it.action()
        body = it
    }
}

inline fun <T: HtmlBodyHTagBase> HtmlBodyGroup<HtmlBody>.h(hTagInstance: T, text: String, action: T.() -> Unit): HtmlBodyGroup<HtmlBody> {
    hTagInstance.also {
        it.text = text
        it.action()
        addHtmlBody(it)
    }
    return this
}
