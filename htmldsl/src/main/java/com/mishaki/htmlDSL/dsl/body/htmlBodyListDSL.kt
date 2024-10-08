package com.mishaki.htmlDSL.dsl.body

import com.mishaki.htmlDSL.entity.body.base.HtmlBody
import com.mishaki.htmlDSL.entity.body.base.HtmlBodyGroup
import com.mishaki.htmlDSL.entity.body.tag.list.HtmlBodyListLITag
import com.mishaki.htmlDSL.entity.body.tag.list.HtmlBodyListOLTag
import com.mishaki.htmlDSL.entity.body.tag.list.HtmlBodyListULOLBaseTag
import com.mishaki.htmlDSL.entity.body.tag.list.HtmlBodyListULTag

inline fun HtmlBodyGroup<HtmlBody>.ol(action: HtmlBodyListOLTag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    getOL.also {
        it.action()
        addHtmlBody(it)
    }
    return this
}

inline fun HtmlBodyGroup<HtmlBody>.ul(action: HtmlBodyListULTag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    getUL.also {
        it.action()
        addHtmlBody(it)
    }
    return this
}

inline fun HtmlBodyListULOLBaseTag.li(action: HtmlBodyListLITag.() -> Unit): HtmlBodyListULOLBaseTag {
    HtmlBodyListLITag().also {
        it.action()
        addHtmlBody(it)
    }
    return this
}