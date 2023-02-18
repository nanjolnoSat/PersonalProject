package com.mishaki.personalproject.recyclerexpandableadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mishaki.personalproject.R
import com.mishaki.recyclerexpanableadapter.FloatItemDecoration
import kotlinx.android.synthetic.main.activity_second_list.*

class SecondListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_list)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = SecondListAdapter()
        val title1 = "title1"
        val title2 = "title2"
        val title3 = "title3"
        val title4 = "title4"
        val title5 = "title5"
        adapter.parentList.addAll(arrayListOf(title1, title2, title3, title4, title5))
        adapter.childMap[title1] = 10
        adapter.childMap[title2] = 10
        adapter.childMap[title3] = 10
        adapter.childMap[title4] = 10
        adapter.childMap[title5] = 10
        recycler.adapter = adapter
        recycler.addItemDecoration(FloatItemDecoration(recycler))
        adapter.notifyDataSetChanged()
    }
}