package com.mishaki.personalproject.tableview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mishaki.personalproject.R
import com.mishaki.tableview.ScrollManager
import com.mishaki.tableview.TabScrollBar
import kotlinx.android.synthetic.main.activity_table.*

class TableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)
        val scrollManager = ScrollManager(this, this)
        header_layout.setScrollManager(scrollManager)
        val adapter = TableAdapter()
        adapter.scrollManager = scrollManager
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val scrollBar = TabScrollBar(recycler)
        recycler.addItemDecoration(scrollBar)
        scrollManager.setScrollBar(scrollBar)
    }
}