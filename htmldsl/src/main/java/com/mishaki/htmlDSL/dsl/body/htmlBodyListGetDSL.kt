package com.mishaki.htmlDSL.dsl.body

import com.mishaki.htmlDSL.entity.body.tag.list.HtmlBodyListOLTag
import com.mishaki.htmlDSL.entity.body.tag.list.HtmlBodyListULTag

inline val getOL: HtmlBodyListOLTag
    get() = HtmlBodyListOLTag()

inline val getUL: HtmlBodyListULTag
    get() = HtmlBodyListULTag()