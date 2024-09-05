package com.mishaki.htmlDSL.entity.header.base

abstract class HtmlHeaderGroup<T: HtmlHeader>: HtmlHeader {
    protected open var internalHeaderList: MutableList<T> = ArrayList()

    open fun addHtmlHeader(header: T){
        internalHeaderList.add(header)
    }

    open fun addAllHtmlHeader(list: List<T>){
        internalHeaderList.addAll(list)
    }

    open fun removeHtmlHeader(header: T){
        internalHeaderList.remove(header)
    }

    open fun clearHeaderList(){
        internalHeaderList.clear()
    }

    open fun getHeaderList(): List<T>{
        return internalHeaderList
    }
}
