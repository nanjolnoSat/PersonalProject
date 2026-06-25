package com.mishaki.htmlDSL.dsl.body

import com.mishaki.htmlDSL.entity.body.tag.general.HtmlBodyATag
import com.mishaki.htmlDSL.entity.body.tag.general.HtmlBodyBTag
import com.mishaki.htmlDSL.entity.body.tag.general.HtmlBodyBrTag
import com.mishaki.htmlDSL.entity.body.tag.general.HtmlBodyDivTag
import com.mishaki.htmlDSL.entity.body.tag.general.HtmlBodyHrTag
import com.mishaki.htmlDSL.entity.body.tag.general.HtmlBodyITag
import com.mishaki.htmlDSL.entity.body.tag.general.HtmlBodyImgTag
import com.mishaki.htmlDSL.entity.body.tag.general.HtmlBodyPTag
import com.mishaki.htmlDSL.entity.body.tag.general.HtmlBodyTextTag
import com.mishaki.htmlDSL.entity.body.tag.general.HtmlBodyUTag

val getA: HtmlBodyATag
    get() = HtmlBodyATag()

val getBr: HtmlBodyBrTag
    get() = HtmlBodyBrTag()

val getB: HtmlBodyBTag
    get() = HtmlBodyBTag()

val getDiv: HtmlBodyDivTag
    get() = HtmlBodyDivTag()

val getHr: HtmlBodyHrTag
    get() = HtmlBodyHrTag()

val getImg: HtmlBodyImgTag
    get() = HtmlBodyImgTag()

val getI: HtmlBodyITag
    get() = HtmlBodyITag()

val getP: HtmlBodyPTag
    get() = HtmlBodyPTag()

val getText: HtmlBodyTextTag
    get() = HtmlBodyTextTag()

val getU: HtmlBodyUTag
    get() = HtmlBodyUTag()