package com.mishaki.galgamehelper.html.dsl.body

import com.mishaki.galgamehelper.html.entity.body.HtmlBodyRoot
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodyGroup
import com.mishaki.galgamehelper.html.entity.body.tag.header.*

inline fun HtmlBodyGroup<HtmlBody>.h1(text: String): HtmlBodyH1Tag {
    return h1(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.h1(action: HtmlBodyH1Tag.() -> Unit): HtmlBodyH1Tag {
    return h1("", action)
}

inline fun HtmlBodyGroup<HtmlBody>.h1(text: String, action: HtmlBodyH1Tag.() -> Unit): HtmlBodyH1Tag {
    return getH1.also {
        it.value = text
        it.action()
        addHtmlBody(it)
    }
}

inline fun HtmlBodyGroup<HtmlBody>.h2(text: String): HtmlBodyH2Tag {
    return h2(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.h2(action: HtmlBodyH2Tag.() -> Unit): HtmlBodyH2Tag {
    return h2("", action)
}

inline fun HtmlBodyGroup<HtmlBody>.h2(text: String, action: HtmlBodyH2Tag.() -> Unit): HtmlBodyH2Tag {
    return getH2.also {
        it.value = text
        it.action()
        addHtmlBody(it)
    }
}

inline fun HtmlBodyGroup<HtmlBody>.h3(text: String): HtmlBodyH3Tag {
    return h3(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.h3(action: HtmlBodyH3Tag.() -> Unit): HtmlBodyH3Tag {
    return h3("", action)
}

inline fun HtmlBodyGroup<HtmlBody>.h3(text: String, action: HtmlBodyH3Tag.() -> Unit): HtmlBodyH3Tag {
    return getH3.also {
        it.value = text
        it.action()
        addHtmlBody(it)
    }
}

inline fun HtmlBodyGroup<HtmlBody>.h4(text: String): HtmlBodyH4Tag {
    return h4(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.h4(action: HtmlBodyH4Tag.() -> Unit): HtmlBodyH4Tag {
    return h4("", action)
}

inline fun HtmlBodyGroup<HtmlBody>.h4(text: String, action: HtmlBodyH4Tag.() -> Unit): HtmlBodyH4Tag {
    return getH4.also {
        it.value = text
        it.action()
        addHtmlBody(it)
    }
}

inline fun HtmlBodyGroup<HtmlBody>.h5(text: String): HtmlBodyH5Tag {
    return h5(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.h5(action: HtmlBodyH5Tag.() -> Unit): HtmlBodyH5Tag {
    return h5("", action)
}

inline fun HtmlBodyGroup<HtmlBody>.h5(text: String, action: HtmlBodyH5Tag.() -> Unit): HtmlBodyH5Tag {
    return getH5.also {
        it.value = text
        it.action()
        addHtmlBody(it)
    }
}

inline fun HtmlBodyGroup<HtmlBody>.h6(text: String): HtmlBodyH6Tag {
    return h6(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.h6(action: HtmlBodyH6Tag.() -> Unit): HtmlBodyH6Tag {
    return h6("", action)
}

inline fun HtmlBodyGroup<HtmlBody>.h6(text: String, action: HtmlBodyH6Tag.() -> Unit): HtmlBodyH6Tag {
    return getH6.also {
        it.value = text
        it.action()
        addHtmlBody(it)
    }
}
