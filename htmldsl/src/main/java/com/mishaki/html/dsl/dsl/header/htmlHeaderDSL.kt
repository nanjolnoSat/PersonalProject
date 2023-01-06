package com.mishaki.galgamehelper.html.dsl

import com.mishaki.galgamehelper.html.entity.body.HtmlBodyRoot
import com.mishaki.galgamehelper.html.entity.header.HtmlHeaderRoot
import com.mishaki.galgamehelper.html.entity.header.HtmlHeaderTitle
import com.mishaki.galgamehelper.html.entity.style.HtmlStyleRoot

inline fun HtmlHeaderRoot.style(styleAction: HtmlStyleRoot.() -> Unit): HtmlStyleRoot{
    return HtmlStyleRoot().also {
        it.styleAction()
        style = it
    }
}

inline fun HtmlHeaderRoot.title(title: String): HtmlHeaderTitle {
    return HtmlHeaderTitle(title).also {
        this.title = it
    }
}

inline fun HtmlHeaderRoot.title(title: String, action: HtmlHeaderTitle.() -> Unit): HtmlHeaderTitle {
    return HtmlHeaderTitle(title).also {
        it.action()
        this.title = it
    }
}
