package com.mishaki.htmlDSL.dsl.body

import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTBodyTag
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTFootTag
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTHead
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTag
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTdTag
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableThTag
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTrTag

inline val getTable: HtmlBodyTableTag
    get() = HtmlBodyTableTag()

inline val getTHead: HtmlBodyTableTHead
    get() = HtmlBodyTableTHead()

inline val getTBody: HtmlBodyTableTBodyTag
    get() = HtmlBodyTableTBodyTag()

inline val getTFoot: HtmlBodyTableTFootTag
    get() = HtmlBodyTableTFootTag()

inline val getTr: HtmlBodyTableTrTag
    get() = HtmlBodyTableTrTag()

inline val getTh: HtmlBodyTableThTag
    get() = HtmlBodyTableThTag()

inline val getTd: HtmlBodyTableTdTag
    get() = HtmlBodyTableTdTag()
