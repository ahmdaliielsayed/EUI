package com.example.eui.day2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eui.R

//class MyAdapter(private val datasource: List<Day>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
class MyAdapter : ListAdapter<Day, MyAdapter.ViewHolder>(RowItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewObject = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        val holder = ViewHolder(viewObject)

        holder.row.setOnClickListener {
            Toast.makeText(parent.context, "Clicked: ${getItem(holder.adapterPosition).name}", Toast.LENGTH_SHORT).show()
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.txtNumber.text = datasource[position].name
//        holder.imageView.setImageResource(datasource[position].image)
        holder.txtNumber.text = getItem(position).name
        holder.imageView.setImageResource(getItem(position).image)
    }

    class ViewHolder(val row: View): RecyclerView.ViewHolder(row) {
        val txtNumber = row.findViewById<TextView>(R.id.txt_number)
        val imageView = row.findViewById<ImageView>(R.id.imageView)
    }

    class RowItemDiffCallback: DiffUtil.ItemCallback<Day>() {

        /*
        Day("Saturday", R.drawable.seven),
        --> Day("sunday", R.drawable.one),
        Day("Monday", R.drawable.one), Day("Monday", R.drawable.one)
        Day("Tuesday", R.drawable.one),
        Day("Wednesday", R.drawable.one),
        --> Day("Thursday", R.drawable.one) --> Day("Thursday", R.drawable.seven),
        Day("Friday", R.drawable.seven),
        * */

        override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean { // true
            // unique id
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean { // true
            return oldItem == newItem
        }
    }
}