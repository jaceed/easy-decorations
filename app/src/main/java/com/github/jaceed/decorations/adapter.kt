package com.github.jaceed.decorations

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.jaceed.decorations.linear.LinearDecoration
import kotlinx.android.synthetic.main.layout_item.view.*

class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val item: TextView = itemView.tv
}

class ItemAdapter(private val size: Int, private val orientation: Int = LinearDecoration.VERTICAL) : RecyclerView.Adapter<ItemHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(if (orientation == LinearDecoration.VERTICAL) R.layout.layout_item else R.layout.layout_item_h, parent, false))
    }

    override fun getItemCount(): Int {
        return size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.item.text = "item $position"
    }

}

