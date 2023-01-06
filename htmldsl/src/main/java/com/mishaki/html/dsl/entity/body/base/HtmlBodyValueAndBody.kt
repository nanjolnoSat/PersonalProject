package com.mishaki.galgamehelper.html.entity.body.base

interface HtmlBodyValueAndBody<V, B: HtmlBody> {
    var value: V?
    var valueBody: B?
}