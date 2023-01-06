package com.mishaki.galgamehelper.html.entity.body.tag.table

import com.mishaki.galgamehelper.html.dsl.generateAttributeString
import com.mishaki.galgamehelper.html.dsl.generateValueAndAttributeCode
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyAttribute
import com.mishaki.galgamehelper.html.entity.body.attribute.HtmlBodyIdAttribute

class HtmlBodyTableTdTag: HtmlBodyTableThTdTag() {
    override fun getTagString(): String = "td"
}
