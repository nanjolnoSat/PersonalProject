package com.mishaki.galgamehelper.html.dsl

import com.mishaki.galgamehelper.html.entity.header.entity.HtmlHeaderLink
import com.mishaki.galgamehelper.html.entity.header.HtmlHeaderRoot
import com.mishaki.galgamehelper.html.entity.header.entity.HtmlHeaderTitle
import com.mishaki.galgamehelper.html.entity.style.HtmlStyleRoot

inline fun HtmlHeaderRoot.style(styleAction: HtmlStyleRoot.() -> Unit) {
    HtmlStyleRoot().also {
        it.styleAction()
        style = it
    }
}

inline fun HtmlHeaderRoot.title(title: String): HtmlHeaderRoot {
    return title(title) {}
}

inline fun HtmlHeaderRoot.title(title: String, action: HtmlHeaderTitle.() -> Unit): HtmlHeaderRoot {
    HtmlHeaderTitle(title).also {
        it.action()
        this.title = it
    }
    return this
}

inline fun HtmlHeaderRoot.link(action: HtmlHeaderLink.() -> Unit): HtmlHeaderRoot{
    HtmlHeaderLink().also {
        it.action()
        addHtmlHeader(it)
    }
    return this
}