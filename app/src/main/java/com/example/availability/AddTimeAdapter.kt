package com.example.availability

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.availability.Models.ItemDataEntity
import com.example.availability.Models.itemAdd

class AddTimeAdapter(
    private val context: Context,
    private val listener: OnItemClickListener,
    private val list: MutableList<itemAdd> // Make this mutable to handle changes
) : RecyclerView.Adapter<AddTimeAdapter.holder1>() {

    class holder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val delete = itemView.findViewById<ImageView>(R.id.delete)
        val add = itemView.findViewById<ImageView>(R.id.add)
        var num = itemView.findViewById<TextView>(R.id.num)
        var spinner1 = itemView.findViewById<Spinner>(R.id.spinner1)
        var spinner2 = itemView.findViewById<Spinner>(R.id.Spinner2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder1 {
        return holder1(LayoutInflater.from(parent.context).inflate(R.layout.time_set_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: holder1, position: Int) {
        val item = list[position]
        holder.num.text = (position+1).toString()
        holder.delete.setOnClickListener {
            listener.onItemDelete(position) // Notify the activity/fragment
        }
        holder.add.setOnClickListener {
            listener.onItemClicked(position) // Notify the activity
        }
        for (i in 0..position){
            if (i == list.size-1){
                holder.add.visibility = View.VISIBLE
            }else{
                holder.add.visibility = View.GONE

            }
        }
       spinnerView1(holder, position)
       spinnerView2(holder, position)
    }

    private fun spinnerView2(holder: holder1, position: Int) {
        val coursesList = listOf("01:00 AM", "02:00 AM", "03:00 AM", "04:00 AM",
            "05:00 AM", "06:00 AM", "07:00 AM", "08:00 AM","09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
            "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM",
            "05:00 PM", "06:00 PM", "07:00 PM", "08:00 PM","09:00 PM", "10:00 PM", "11:00 PM", "12:00 AM")

        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            coursesList
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.spinner1.adapter = adapter
        holder.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position) as String
               // Toast.makeText(context, selectedItem, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //Toast.makeText(context, parent, Toast.LENGTH_SHORT).show()

            }
        }
    }


    private fun spinnerView1(holder: holder1, position: Int) {
        val coursesList = listOf("01:00 AM", "02:00 AM", "03:00 AM", "04:00 AM",
            "05:00 AM", "06:00 AM", "07:00 AM", "08:00 AM","09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
            "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM",
        "05:00 PM", "06:00 PM", "07:00 PM", "08:00 PM","09:00 PM", "10:00 PM", "11:00 PM", "12:00 AM"
        )

        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            coursesList
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.spinner2.adapter = adapter
        holder.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position) as String
               //Toast.makeText(context, selectedItem, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //Toast.makeText(context, parent, Toast.LENGTH_SHORT).show()

            }
        }
    }
}
interface OnItemClickListener {
    fun onItemClicked(position: Int)
    fun onItemDelete(position: Int)
}

