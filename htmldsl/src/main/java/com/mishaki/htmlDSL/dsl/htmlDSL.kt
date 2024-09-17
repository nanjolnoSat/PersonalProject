package com.mishaki.htmlDSL.dsl

import com.mishaki.htmlDSL.entity.body.HtmlBodyRoot
import com.mishaki.htmlDSL.entity.HtmlRoot
import com.mishaki.htmlDSL.entity.header.HtmlHeadRoot

inline fun html(action: HtmlRoot.() -> Unit): HtmlRoot {
    return HtmlRoot().also {
        it.action()
    }
}

inline fun HtmlRoot.header(action: HtmlHeadRoot.() -> Unit): HtmlHeadRoot {
    return HtmlHeadRoot().also {
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
