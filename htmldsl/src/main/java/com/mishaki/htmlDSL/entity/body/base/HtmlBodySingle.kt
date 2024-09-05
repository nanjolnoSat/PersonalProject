package com.mishaki.htmlDSL.entity.body.base

abstract class HtmlBodySingle<T: HtmlBody>: HtmlBody {
    open var body: T? = null
}