package com.example.eui.day3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eui.R
import com.example.eui.day4.Product

class MySecondAdapter : ListAdapter<Product, MySecondAdapter.ViewHolder>(RowItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewObject = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        val holder = ViewHolder(viewObject)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtNumber.text = getItem(position).title
        Glide.with(holder.row)
            .load(getItem(position).thumbnail)
            .into(holder.imageView)
    }

    class ViewHolder(val row: View): RecyclerView.ViewHolder(row) {
        val txtNumber = row.findViewById<TextView>(R.id.txt_number)
        val imageView = row.findViewById<ImageView>(R.id.imageView)
    }

    class RowItemDiffCallback: DiffUtil.ItemCallback<Product>() {

        /*
        Day("Saturday", R.drawable.seven),
        --> Day("sunday", R.drawable.one),
        Day("Monday", R.drawable.one), Day("Monday", R.drawable.one)
        Day("Tuesday", R.drawable.one),
        Day("Wednesday", R.drawable.one),
        --> Day("Thursday", R.drawable.one) --> Day("Thursday", R.drawable.seven),
        Day("Friday", R.drawable.seven),
        * */

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean { // true
            // unique id
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean { // true
            return oldItem == newItem
        }
    }
}