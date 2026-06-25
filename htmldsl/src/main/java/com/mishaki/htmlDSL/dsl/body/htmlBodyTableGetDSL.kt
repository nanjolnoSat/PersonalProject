package com.mishaki.htmlDSL.dsl.body

import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTBodyTag
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTFootTag
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTHead
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTag
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTdTag
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableThTag
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTrTag

val getTable: HtmlBodyTableTag
    get() = HtmlBodyTableTag()

val getTHead: HtmlBodyTableTHead
    get() = HtmlBodyTableTHead()

val getTBody: HtmlBodyTableTBodyTag
    get() = HtmlBodyTableTBodyTag()

val getTFoot: HtmlBodyTableTFootTag
    get() = HtmlBodyTableTFootTag()

val getTr: HtmlBodyTableTrTag
    get() = HtmlBodyTableTrTag()

val getTh: HtmlBodyTableThTag
    get() = HtmlBodyTableThTag()

val getTd: HtmlBodyTableTdTag
    get() = HtmlBodyTableTdTag()
