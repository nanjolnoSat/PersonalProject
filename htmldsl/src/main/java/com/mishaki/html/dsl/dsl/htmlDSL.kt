package com.mishaki.galgamehelper.html.dsl

import com.mishaki.galgamehelper.html.entity.body.HtmlBodyRoot
import com.mishaki.galgamehelper.html.entity.HtmlRoot
import com.mishaki.galgamehelper.html.entity.header.HtmlHeaderRoot

inline fun html(action: HtmlRoot.() -> Unit): HtmlRoot {
    return HtmlRoot().also {
        it.action()
    }
}

inline fun HtmlRoot.header(action: HtmlHeaderRoot.() -> Unit): HtmlHeaderRoot {
    return HtmlHeaderRoot().also {
        it.action()
        header = it
    }
}

inline fun HtmlRoot.body(action: HtmlBodyRoot.() -> Unit): HtmlBodyRoot {
    return HtmlBodyRoot().also {
        it.action()
        body = it
    }
}
