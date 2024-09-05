package com.mishaki.htmlDSL.entity.body.attribute

class HtmlBodyAttribute {
    object general {
        const val ID = "id"
        const val WIDTH = "width"
        const val HEIGHT = "height"
    }

    object table {
        const val BORDER = "border"
        const val CELL_PADDING = "cellpadding"
        const val CELL_SPACING = "cellspacing"
        const val COL_SPAN = "colspan"
    }

    object a {
        const val HREF = "href"
        const val TARGET = "target"
    }

    object img{
        const val SRC = "src"
    }
}
