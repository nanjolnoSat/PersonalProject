package com.mishaki.htmlDSL.dsl.body

import com.mishaki.htmlDSL.entity.body.base.HtmlBody
import com.mishaki.htmlDSL.entity.body.base.HtmlBodyGroup
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTBodyTag
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTFootTag
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTHead
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTag
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTdTag
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableThTag
import com.mishaki.htmlDSL.entity.body.tag.table.HtmlBodyTableTrTag

inline fun HtmlBodyGroup<HtmlBody>.table(tableAction: HtmlBodyTableTag.() -> Unit): HtmlBodyGroup<HtmlBody> {
    getTable.also {
        it.tableAction()
        addHtmlBody(it)
    }
    return this
}

inline fun HtmlBodyTableTag.thead(theadAction: HtmlBodyTableTHead.() -> Unit): HtmlBodyTableTag {
    getTHead.also {
        it.theadAction()
        thead = it
    }
    return this
}

inline fun HtmlBodyTableTag.tbody(action: HtmlBodyTableTBodyTag.() -> Unit): HtmlBodyTableTag {
    getTBody.also {
        it.action()
        tbody = it
    }
    return this
}

inline fun HtmlBodyTableTag.tfoot(tfootAction: HtmlBodyTableTFootTag.() -> Unit): HtmlBodyTableTag {
    getTFoot.also {
        it.tfootAction()
        tfoot = it
    }
    return this
}

inline fun HtmlBodyTableTHead.tr(trAction: HtmlBodyTableTrTag.() -> Unit): HtmlBodyTableTHead {
    getTr.also {
        it.trAction()
        addHtmlBody(it)
    }
    return this
}

inline fun HtmlBodyTableTBodyTag.tr(trAction: HtmlBodyTableTrTag.() -> Unit): HtmlBodyTableTBodyTag {
    getTr.also {
        it.trAction()
        addHtmlBody(it)
    }
    return this
}

inline fun HtmlBodyTableTFootTag.tr(trAction: HtmlBodyTableTrTag.() -> Unit): HtmlBodyTableTFootTag {
    getTr.also {
        it.trAction()
        addHtmlBody(it)
    }
    return this
}

inline fun HtmlBodyTableTrTag.th(value: String): HtmlBodyTableTrTag {
    return th(value) {}
}

inline fun HtmlBodyTableTrTag.th(value: String, action: HtmlBodyTableThTag.() -> Unit): HtmlBodyTableTrTag {
    getTh.also {
        it.text(value)
        it.action()
        addHtmlBody(it)
    }
    return this
}

inline fun HtmlBodyTableTrTag.td(value: String): HtmlBodyTableTrTag {
    return td(value){}
}

inline fun HtmlBodyTableTrTag.td(value: String, action: HtmlBodyTableTdTag.() -> Unit): HtmlBodyTableTrTag {
    getTd.also {
        it.text(value)
        it.action()
        addHtmlBody(it)
    }
    return this
}
