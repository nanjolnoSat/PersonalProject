package com.mishaki.galgamehelper.html.dsl.body

import com.mishaki.galgamehelper.html.entity.body.tag.table.*

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
