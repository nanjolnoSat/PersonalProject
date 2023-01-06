package com.mishaki.galgamehelper.html.dsl

import com.mishaki.galgamehelper.html.dsl.body.*
import com.mishaki.galgamehelper.html.entity.body.HtmlBodyRoot
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody
import com.mishaki.galgamehelper.html.entity.body.base.HtmlBodyGroup
import com.mishaki.galgamehelper.html.entity.body.tag.general.*
import com.mishaki.galgamehelper.html.entity.style.HtmlStyleRoot

inline fun HtmlBodyRoot.style(styleAction: HtmlStyleRoot.() -> Unit): HtmlStyleRoot{
    return HtmlStyleRoot().also {
        it.styleAction()
        style = it
    }
}

inline fun HtmlBodyGroup<HtmlBody>.a(anchorAction: HtmlBodyATag.() -> Unit): HtmlBodyATag {
    return getA.also {
        it.anchorAction()
        addHtmlBody(it)
    }
}

inline fun HtmlBodyGroup<HtmlBody>.b(text: String): HtmlBodyBTag {
    return b(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.b(text: String, action: HtmlBodyBTag.() -> Unit): HtmlBodyBTag {
    return getB.also {
        it.value = text
        it.action()
        addHtmlBody(it)
    }
}

inline fun HtmlBodyGroup<HtmlBody>.br(): HtmlBodyBrTag {
    return getBr.also {
        addHtmlBody(it)
    }
}

inline fun HtmlBodyGroup<HtmlBody>.div(action: HtmlBodyDivTag.() -> Unit): HtmlBodyDivTag {
    return getDiv.also {
        it.action()
        addHtmlBody(it)
    }
}

inline fun HtmlBodyGroup<HtmlBody>.hr(): HtmlBodyHrTag {
    return getHr.also {
        addHtmlBody(it)
    }
}

inline fun HtmlBodyGroup<HtmlBody>.i(text: String): HtmlBodyITag {
    return i(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.i(text: String, action: HtmlBodyITag.() -> Unit): HtmlBodyITag {
    return getI.also {
        it.value = text
        it.action()
        addHtmlBody(it)
    }
}

inline fun HtmlBodyGroup<HtmlBody>.p(text: String): HtmlBodyPTag {
    return p(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.p(text: String, action: HtmlBodyPTag.() -> Unit): HtmlBodyPTag {
    return getP.also {
        it.value = text
        it.action()
        addHtmlBody(it)
    }
}

inline fun HtmlBodyGroup<HtmlBody>.text(text: String): HtmlBodyTextTag {
    return getText.also {
        it.value = text
        addHtmlBody(it)
    }
}

inline fun HtmlBodyGroup<HtmlBody>.u(text: String): HtmlBodyUTag {
    return u(text) {}
}

inline fun HtmlBodyGroup<HtmlBody>.u(text: String, action: HtmlBodyUTag.() -> Unit): HtmlBodyUTag {
    return getU.also {
        it.value = text
        it.action()
        addHtmlBody(it)
    }
}
