package com.mishaki.galgamehelper.html.dsl

import com.mishaki.galgamehelper.html.dsl.body.getTable
import com.mishaki.galgamehelper.html.entity.body.HtmlBodyRoot
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodyGroup
import com.mishaki.galgamehelper.html.entity.body.tag.table.*

inline fun HtmlBodyGroup<HtmlBody>.table(tableAction: HtmlBodyTableTag.() -> Unit): HtmlBodyTableTag {
    return getTable.also {
        it.tableAction()
        addHtmlBody(it)
    }
}

inline fun HtmlBodyTableTag.thead(theadAction: HtmlBodyTableTHead.() -> Unit): HtmlBodyTableTHead {
    return HtmlBodyTableTHead().also {
        it.theadAction()
        thead = it
    }
}

inline fun HtmlBodyTableTag.tbody(tbodyAction: HtmlBodyTableTBodyTag.() -> Unit): HtmlBodyTableTBodyTag {
    return HtmlBodyTableTBodyTag().also {
        it.tbodyAction()
        tbody = it
    }

}

inline fun HtmlBodyTableTag.tfoot(tfootAction: HtmlBodyTableTFootTag.() -> Unit): HtmlBodyTableTFootTag {
    return HtmlBodyTableTFootTag().also {
        it.tfootAction()
        tfoot = it
    }
}

inline fun HtmlBodyTableTHead.tr(trAction: HtmlBodyTableTrTag.() -> Unit): HtmlBodyTableTrTag {
    return HtmlBodyTableTrTag().also {
        it.trAction()
        addHtmlBody(it)
    }
}

inline fun HtmlBodyTableTBodyTag.tr(trAction: HtmlBodyTableTrTag.() -> Unit): HtmlBodyTableTrTag {
    return HtmlBodyTableTrTag().also {
        it.trAction()
        addHtmlBody(it)
    }
}

inline fun HtmlBodyTableTFootTag.tr(trAction: HtmlBodyTableTrTag.() -> Unit): HtmlBodyTableTrTag {
    return HtmlBodyTableTrTag().also {
        it.trAction()
        addHtmlBody(it)
    }
}

inline fun HtmlBodyTableTrTag.th(value: String): HtmlBodyTableThTag {
    return th(value) {}
}

inline fun HtmlBodyTableTrTag.th(value: String, thAction: HtmlBodyTableThTag.() -> Unit): HtmlBodyTableThTag {
    return HtmlBodyTableThTag().also {
        it.value = value
        it.thAction()
        addHtmlBody(it)
    }
}

inline fun HtmlBodyTableTrTag.td(value: String): HtmlBodyTableTdTag {
    return td(value){}
}

inline fun HtmlBodyTableTrTag.td(value: String, tdAction: HtmlBodyTableTdTag.() -> Unit): HtmlBodyTableTdTag {
    return HtmlBodyTableTdTag().also {
        it.value = value
        it.tdAction()
        addHtmlBody(it)
    }
}
