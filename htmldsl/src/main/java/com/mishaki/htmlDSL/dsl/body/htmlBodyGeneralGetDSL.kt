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

inline val getA: HtmlBodyATag
    get() = HtmlBodyATag()

inline val getBr: HtmlBodyBrTag
    get() = HtmlBodyBrTag()

inline val getB: HtmlBodyBTag
    get() = HtmlBodyBTag()

inline val getDiv: HtmlBodyDivTag
    get() = HtmlBodyDivTag()

inline val getHr: HtmlBodyHrTag
    get() = HtmlBodyHrTag()

inline val getImg: HtmlBodyImgTag
    get() = HtmlBodyImgTag()

inline val getI: HtmlBodyITag
    get() = HtmlBodyITag()

inline val getP: HtmlBodyPTag
    get() = HtmlBodyPTag()

inline val getText: HtmlBodyTextTag
    get() = HtmlBodyTextTag()

inline val getU: HtmlBodyUTag
    get() = HtmlBodyUTag()