package com.mishaki.htmlDSL.dsl.body

import com.mishaki.htmlDSL.entity.body.tag.list.HtmlBodyListOLTag
import com.mishaki.htmlDSL.entity.body.tag.list.HtmlBodyListULTag

val getOL: HtmlBodyListOLTag
    get() = HtmlBodyListOLTag()

val getUL: HtmlBodyListULTag
    get() = HtmlBodyListULTag()