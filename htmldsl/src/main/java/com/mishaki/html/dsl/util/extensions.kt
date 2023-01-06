package com.mishaki.galgamehelper.html.util

import com.mishaki.galgamehelper.html.entity.body.base.HtmlBody

fun <T: HtmlBody> MutableList<T>.bodyListToString() = joinToString("") {it.toHtmlCode()}
