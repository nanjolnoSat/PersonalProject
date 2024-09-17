package com.mishaki.htmlDSL.dsl.header

import com.mishaki.htmlDSL.entity.header.entity.HtmlHeaderLink
import com.mishaki.htmlDSL.entity.header.HtmlHeadRoot
import com.mishaki.htmlDSL.entity.header.entity.HtmlHeaderTitle
import com.mishaki.htmlDSL.entity.style.HtmlStyleRoot

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

inline fun HtmlHeadRoot.link(action: HtmlHeaderLink.() -> Unit): HtmlHeadRoot {
    HtmlHeaderLink().also {
        it.action()
        addHtmlHeader(it)
    }
    return this
}