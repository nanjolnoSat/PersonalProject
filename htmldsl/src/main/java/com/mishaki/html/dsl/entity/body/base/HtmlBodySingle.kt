package com.mishaki.galgamehelper.html.entity.body.base

abstract class HtmlBodySingle<T: HtmlBody>: HtmlBody {
    open var body: T? = null
}