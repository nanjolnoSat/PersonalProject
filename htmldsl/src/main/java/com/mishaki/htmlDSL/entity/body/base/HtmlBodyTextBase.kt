package com.mishaki.htmlDSL.entity.body.base

import com.mishaki.htmlDSL.dsl.body.getText
import com.mishaki.htmlDSL.entity.body.tag.general.HtmlBodyTextTag

abstract class HtmlBodyTextBase: HtmlBodySingle<HtmlBodyTextTag>() {
    var text: String = ""
        set(value) {
            if(field == value){
                return
            }
            field = value
            val body = body ?: getText.apply {
                body = this
            }
            body.text = value
        }
}