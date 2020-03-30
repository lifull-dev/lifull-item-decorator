package com.lifull.android.lifullitemdecoration

import android.app.Activity
import android.os.Bundle
import com.lifull.android.itemdecoration.FooterItemDecoration
import com.lifull.android.itemdecoration.HeaderItemDecoration
import com.lifull.android.itemdecoration.SectionFooterItemDecoration
import com.lifull.android.itemdecoration.SectionHeaderItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_item.view.*
import kotlinx.android.synthetic.main.vh_sample.view.*

class MainActivity: Activity() {

    private val headerItemDecoration: HeaderItemDecoration by lazy {
        HeaderItemDecoration(
            R.layout.layout_item,
            onBindItemDecoration = { itemView, child, parent ->
                itemView.textView.text = "Header"
            })
    }

    private val footerItemDecoration: FooterItemDecoration by lazy {
        FooterItemDecoration(
            R.layout.layout_item,
            onBindItemDecoration = { itemView, child, parent ->
                itemView.textView.text = "Footer"
            })
    }

    private val headerSectionItemDecoration: SectionHeaderItemDecoration by lazy {
        SectionHeaderItemDecoration(
            R.layout.layout_item,
            condition = { child, parent ->
                // example
                return@SectionHeaderItemDecoration parent.getChildViewHolder(child).itemViewType == 0
            },
            onBindItemDecoration = { itemView, child, parent ->
                val position = parent.getChildAdapterPosition(child)
                child.textView_label.text = position.toString()
                itemView.textView.text = "Section Header position:%d".format(position)
            })
    }

    private val footerSectionItemDecoration: SectionFooterItemDecoration by lazy {
        SectionFooterItemDecoration(
            R.layout.layout_item,
            condition = { child, parent ->
                // example
                return@SectionFooterItemDecoration parent.getChildViewHolder(child).itemViewType == 0
            },
            onBindItemDecoration = { itemView, child, parent ->
                val position = parent.getChildAdapterPosition(child)
                child.textView_label.text = position.toString()
                itemView.textView.text = "Section Footer position:%d".format(position)
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = mutableListOf<String>()
        for (i in 0..10) {
            items.add(i.toString())
        }
        val adapter = Adapter(items)
        recycler_view.adapter = adapter

        // Add Header
//        recycler_view.addItemDecoration(headerItemDecoration)
        // Add Footer
//        recycler_view.addItemDecoration(footerItemDecoration)
        // Add Section Header
        recycler_view.addItemDecoration(headerSectionItemDecoration)
        // Add Section Footer
        recycler_view.addItemDecoration(footerSectionItemDecoration)
    }
}