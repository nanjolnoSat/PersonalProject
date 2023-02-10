package com.mishaki.galgamehelper.html.dsl.body

import com.mishaki.galgamehelper.html.entity.body.tag.list.HtmlBodyListOLTag
import com.mishaki.galgamehelper.html.entity.body.tag.list.HtmlBodyListULTag

inline val getOL: HtmlBodyListOLTag
    get() = HtmlBodyListOLTag()

inline val getUL: HtmlBodyListULTag
    get() = HtmlBodyListULTag()