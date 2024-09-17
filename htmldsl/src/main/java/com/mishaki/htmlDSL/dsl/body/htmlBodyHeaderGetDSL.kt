package com.mishaki.htmlDSL.dsl.body

import com.mishaki.htmlDSL.entity.body.tag.header.HtmlBodyH1Tag
import com.mishaki.htmlDSL.entity.body.tag.header.HtmlBodyH2Tag
import com.mishaki.htmlDSL.entity.body.tag.header.HtmlBodyH3Tag
import com.mishaki.htmlDSL.entity.body.tag.header.HtmlBodyH4Tag
import com.mishaki.htmlDSL.entity.body.tag.header.HtmlBodyH5Tag
import com.mishaki.htmlDSL.entity.body.tag.header.HtmlBodyH6Tag

inline val getH1: HtmlBodyH1Tag
    get() = HtmlBodyH1Tag()

inline val getH2: HtmlBodyH2Tag
    get() = HtmlBodyH2Tag()

inline val getH3: HtmlBodyH3Tag
    get() = HtmlBodyH3Tag()

inline val getH4: HtmlBodyH4Tag
    get() = HtmlBodyH4Tag()

inline val getH5: HtmlBodyH5Tag
    get() = HtmlBodyH5Tag()

inline val getH6: HtmlBodyH6Tag
    get() = HtmlBodyH6Tag()
