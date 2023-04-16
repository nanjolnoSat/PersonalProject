package com.mishaki.personalproject.tableview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mishaki.personalproject.R
import com.mishaki.tableview.RecyclerItemTableHorizontalGestureLayout
import com.mishaki.tableview.ScrollManager

class TableAdapter :RecyclerView.Adapter<TableAdapter.ViewHolder>(){
    var scrollManager: ScrollManager? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.table_item_test, parent, false)).also { vh ->
            scrollManager?.also{
                vh.scrollLayout.setScrollManagerAndClickArea(it, vh.clickArea)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            scrollManager?.scrollSpecialView(clickArea)
            clickArea.setOnClickListener {
                Toast.makeText(itemView.context, "toast", Toast.LENGTH_SHORT).show()
            }
            stickItemTv.text = "stickItem$position"
            item1Tv.text = "item$position-1"
            item2Tv.text = "item$position-2"
            item3Tv.text = "item$position-3"
            item4Tv.text = "item$position-4"
            item5Tv.text = "item$position-5"
            item6Tv.text = "item$position-6"
            item7Tv.text = "item$position-7"
            item8Tv.text = "item$position-8"
            item9Tv.text = "item$position-9"
            item10Tv.text = "item$position-10"
        }
    }

    override fun getItemCount(): Int = 100

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val scrollLayout: RecyclerItemTableHorizontalGestureLayout = itemView.findViewById(R.id.scroll_layout)
        val clickArea: View = itemView.findViewById(R.id.click_area)
        val stickItemTv: TextView = itemView.findViewById(R.id.stick_item_tv)
        val item1Tv: TextView = itemView.findViewById(R.id.data1_tv)
        val item2Tv: TextView = itemView.findViewById(R.id.data2_tv)
        val item3Tv: TextView = itemView.findViewById(R.id.data3_tv)
        val item4Tv: TextView = itemView.findViewById(R.id.data4_tv)
        val item5Tv: TextView = itemView.findViewById(R.id.data5_tv)
        val item6Tv: TextView = itemView.findViewById(R.id.data6_tv)
        val item7Tv: TextView = itemView.findViewById(R.id.data7_tv)
        val item8Tv: TextView = itemView.findViewById(R.id.data8_tv)
        val item9Tv: TextView = itemView.findViewById(R.id.data9_tv)
        val item10Tv: TextView = itemView.findViewById(R.id.data10_tv)
    }
}