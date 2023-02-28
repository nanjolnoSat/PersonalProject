package com.mishaki.galgamehelper.html.dsl

import com.mishaki.galgamehelper.html.entity.header.entity.HtmlHeaderLink
import com.mishaki.galgamehelper.html.entity.header.HtmlHeadRoot
import com.mishaki.galgamehelper.html.entity.header.entity.HtmlHeaderTitle
import com.mishaki.galgamehelper.html.entity.style.HtmlStyleRoot

inline fun HtmlHeadRoot.style(styleAction: HtmlStyleRoot.() -> Unit) {
    HtmlStyleRoot().also {
        it.styleAction()
        style = it
    }
}

inline fun HtmlHeadRoot.title(title: String): HtmlHeadRoot {
    return title(title) {}
}

inline fun HtmlHeadRoot.title(title: String, action: HtmlHeaderTitle.() -> Unit): HtmlHeadRoot {
    HtmlHeaderTitle(title).also {
        it.action()
        this.title = it
    }
    return this
}

inline fun HtmlHeadRoot.link(action: HtmlHeaderLink.() -> Unit): HtmlHeadRoot{
    HtmlHeaderLink().also {
        it.action()
        addHtmlHeader(it)
    }
    return this
}