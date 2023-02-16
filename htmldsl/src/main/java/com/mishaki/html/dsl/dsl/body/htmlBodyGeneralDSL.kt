package com.mishaki.galgamehelper.html.dsl

import com.mishaki.galgamehelper.html.dsl.body.*
import com.mishaki.galgamehelper.html.entity.body.HtmlBodyRoot
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodyGroup
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodySingle
import com.mishaki.galgamehelper.html.entity.body.tag.general.*
import com.mishaki.galgamehelper.html.entity.style.HtmlStyleRoot

inline fun HtmlBodyRoot.style(action: HtmlStyleRoot.() -> Unit) {
    HtmlStyleRoot().also {
        it.action()
        style = it
    }
}


inline fun HtmlBodySingle<HtmlBody>.a(action: HtmlBodyATag.() -> Unit) {
    getA.also {
        it.action()
        body = it
    }
}

inline fun HtmlBodyGroup<HtmlBody>.a(action: HtmlBodyATag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    getA.also {
        it.action()
        addHtmlBody(it)
    }
    return this
}

inline fun HtmlBodySingle<HtmlBody>.b(text: String){
    b(text) {}
}

inline fun HtmlBodySingle<HtmlBody>.b(text: String, action: HtmlBodyBTag.() -> Unit){
    getB.also {
        it.text = text
        it.action()
        body = it
    }
}

inline fun HtmlBodyGroup<HtmlBody>.b(text: String): HtmlBodyGroup<HtmlBody> {
    return b(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.b(text: String, action: HtmlBodyBTag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    getB.also {
        it.text = text
        it.action()
        addHtmlBody(it)
    }
    return this
}

inline fun HtmlBodyGroup<HtmlBody>.br(): HtmlBodyGroup<HtmlBody> {
    getBr.also {
        addHtmlBody(it)
    }
    return this
}

inline fun HtmlBodySingle<HtmlBody>.div(action: HtmlBodyDivTag.() -> Unit){
    getDiv.also {
        it.action()
        body = it
    }
}

inline fun HtmlBodyGroup<HtmlBody>.div(action: HtmlBodyDivTag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    getDiv.also {
        it.action()
        addHtmlBody(it)
    }
    return this
}

inline fun HtmlBodyGroup<HtmlBody>.hr(): HtmlBodyGroup<HtmlBody> {
    getHr.also {
        addHtmlBody(it)
    }
    return this
}

inline fun HtmlBodySingle<HtmlBody>.img(action: HtmlBodyImgTag.() -> Unit){
    getImg.also {
        it.action()
        body = it
    }
}

inline fun HtmlBodyGroup<HtmlBody>.img(action: HtmlBodyImgTag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    getImg.also {
        it.action()
        addHtmlBody(it)
    }
    return this
}

inline fun HtmlBodySingle<HtmlBody>.i(text: String){
    i(text) {}
}

inline fun HtmlBodySingle<HtmlBody>.i(text: String, action: HtmlBodyITag.() -> Unit){
    getI.also {
        it.text = text
        it.action()
        body = it
    }
}

inline fun HtmlBodyGroup<HtmlBody>.i(text: String): HtmlBodyGroup<HtmlBody> {
    return i(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.i(text: String, action: HtmlBodyITag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    getI.also {
        it.text = text
        it.action()
        addHtmlBody(it)
    }
    return this
}

inline fun HtmlBodySingle<HtmlBody>.p(text: String){
    p(text) {}
}

inline fun HtmlBodySingle<HtmlBody>.p(text: String, action: HtmlBodyPTag.() -> Unit){
    getP.also {
        it.text = text
        it.action()
        body = it
    }
}

inline fun HtmlBodyGroup<HtmlBody>.p(text: String): HtmlBodyGroup<HtmlBody> {
    return p(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.p(text: String, action: HtmlBodyPTag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    getP.also {
        it.text = text
        it.action()
        addHtmlBody(it)
    }
    return this
}

inline fun HtmlBodySingle<HtmlBody>.text(text: String){
    getText.also {
        it.text = text
        body = it
    }
}

inline var HtmlBodySingle<HtmlBody>.text: String
    get() = (body as? HtmlBodyTextTag)?.text ?: ""
    set(value) {
        val body = body as? HtmlBodyTextTag ?: getText.also {
            body = it
        }
        body.text = value
    }

inline fun HtmlBodyGroup<HtmlBody>.text(text: String): HtmlBodyGroup<HtmlBody> {
    getText.also {
        it.text = text
        addHtmlBody(it)
    }
    return this
}

inline fun HtmlBodySingle<HtmlBody>.u(text: String){
    return u(text) {}
}

inline fun HtmlBodySingle<HtmlBody>.u(text: String, action: HtmlBodyUTag.() -> Unit){
    getU.also {
        it.text = text
        it.action()
        body = it
    }
}

inline fun HtmlBodyGroup<HtmlBody>.u(text: String): HtmlBodyGroup<HtmlBody> {
    return u(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.u(text: String, action: HtmlBodyUTag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    getU.also {
        it.text = text
        it.action()
        addHtmlBody(it)
    }
    return this
}
