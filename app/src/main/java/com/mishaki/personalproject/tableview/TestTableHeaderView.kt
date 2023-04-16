package com.mishaki.personalproject.tableview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.mishaki.personalproject.R
import com.mishaki.tableview.BaseTableHeaderView
import com.mishaki.tableview.TableHorizontalGestureLayout

class TestTableHeaderView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    BaseTableHeaderView(context, attrs, defStyleAttr) {

    private var scrollLayout: TableHorizontalGestureLayout? = null

    override fun getLayoutId(): Int = R.layout.table_header_test

    override fun initLayout(view: View) {
        scrollLayout = view.findViewById(R.id.scroll_layout)
        val stickView: TextView = view.findViewById(R.id.stick_item_tv)
        val data1View: TextView = view.findViewById(R.id.data1_tv)
        val data2View: TextView = view.findViewById(R.id.data2_tv)
        val data3View: TextView = view.findViewById(R.id.data3_tv)
        val data4View: TextView = view.findViewById(R.id.data4_tv)
        val data5View: TextView = view.findViewById(R.id.data5_tv)
        val data6View: TextView = view.findViewById(R.id.data6_tv)
        val data7View: TextView = view.findViewById(R.id.data7_tv)
        val data8View: TextView = view.findViewById(R.id.data8_tv)
        val data9View: TextView = view.findViewById(R.id.data9_tv)
        val data10View: TextView = view.findViewById(R.id.data10_tv)

        stickView.text = "stick"
        data1View.text = "header1"
        data2View.text = "header2"
        data3View.text = "header3"
        data4View.text = "header4"
        data5View.text = "header5"
        data6View.text = "header6"
        data7View.text = "header7"
        data8View.text = "header8"
        data9View.text = "header9"
        data10View.text = "header10"
    }

    override fun getScrollLayout(): TableHorizontalGestureLayout? = scrollLayout
}