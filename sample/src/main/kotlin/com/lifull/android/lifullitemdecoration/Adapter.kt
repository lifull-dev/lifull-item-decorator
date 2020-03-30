package com.lifull.android.lifullitemdecoration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(var items: List<String>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vh_sample, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        items.firstOrNull()
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }
}