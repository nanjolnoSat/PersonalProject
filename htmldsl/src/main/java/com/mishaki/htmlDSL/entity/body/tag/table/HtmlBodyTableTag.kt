package com.mishaki.htmlDSL.entity.body.tag.table

import com.mishaki.htmlDSL.util.generateHtmlCode
import com.mishaki.htmlDSL.entity.body.attribute.HtmlBodyAttribute
import com.mishaki.htmlDSL.entity.body.attribute.HtmlBodyGeneralAttribute
import com.mishaki.htmlDSL.entity.body.attribute.HtmlBodyGeneralAttributeEntity
import com.mishaki.htmlDSL.entity.body.base.HtmlBodyGroup

class HtmlBodyTableTag: HtmlBodyGroup<HtmlBodyTableTHeadTBodyTFoot>(), HtmlBodyTableAttribute {
    override val attributeEntity: HtmlBodyTableAttributeEntity = HtmlBodyTableAttributeEntity()
    var thead: HtmlBodyTableTHead? = null
    var tbody: HtmlBodyTableTBodyTag? = null
    var tfoot: HtmlBodyTableTFootTag? = null

    override fun getTagString(): String = TAG

    override fun toHtmlCode(): String {
        return generateHtmlCode()
    }

    override fun getBodyList(): List<HtmlBodyTableTHeadTBodyTFoot> {
        if(internalBodyList.size >= 3) {
            val (thead, tfoot, tbody) = internalBodyList
            if(this.thead == thead && this.tfoot == tfoot && this.tbody == tbody) {
                return internalBodyList
            }
        }
        internalBodyList.clear()
        thead?.also(internalBodyList::add)
        tfoot?.also(internalBodyList::add)
        tbody?.also(internalBodyList::add)
        return internalBodyList
    }

    override fun getAttributeList(): List<Pair<String, Any>> {
        return super.getAttributeList()
    }

    override fun addHtmlBody(body: HtmlBodyTableTHeadTBodyTFoot) {
    }

    override fun addAllHtmlBody(list: List<HtmlBodyTableTHeadTBodyTFoot>) {
    }

    override fun removeHtmlBody(body: HtmlBodyTableTHeadTBodyTFoot) {
    }

    override fun clearBodyList() {
    }

    companion object{
        const val TAG = "table"
    }
}

interface HtmlBodyTableAttribute: HtmlBodyGeneralAttribute<HtmlBodyTableAttributeEntity> {
    var border: Int
        get() = attributeEntity.border
        set(value) {
            attributeEntity.border = value
        }

    var cellpadding: Int
        get() = attributeEntity.cellpadding
        set(value) {
            attributeEntity.cellpadding = value
        }

    var cellspacing: Int
        get() = attributeEntity.cellspacing
        set(value) {
            attributeEntity.cellspacing = value
        }

    override fun getAttributeList(): List<Pair<String, Any>> {
        val superList = super.getAttributeList()
        val currentList = listOf(
            HtmlBodyAttribute.table.BORDER to attributeEntity.border,
            HtmlBodyAttribute.table.CELL_PADDING to attributeEntity.cellpadding,
            HtmlBodyAttribute.table.CELL_SPACING to attributeEntity.cellspacing,
        )
        return listOf(superList, currentList).flatten()
    }
}

class HtmlBodyTableAttributeEntity: HtmlBodyGeneralAttributeEntity() {
    var border: Int = 10
    var cellpadding: Int = 0
    var cellspacing: Int = 0
}