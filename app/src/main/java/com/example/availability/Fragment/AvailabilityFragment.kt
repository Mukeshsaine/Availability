package com.example.availability.Fragment

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.availability.AddTimeAdapter
import com.example.availability.Models.ItemDataEntity
import com.example.availability.Models.itemAdd
import com.example.availability.OnItemClickListener
import com.example.availability.databinding.FragmentAvailabilityBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AvailabilityFragment : Fragment(), OnItemClickListener{

    lateinit var binding: FragmentAvailabilityBinding
    lateinit var datePicker: DatePicker

    private lateinit var adapter: AddTimeAdapter

    private val itemList = mutableListOf<itemAdd>()  // List of data
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentAvailabilityBinding.inflate(layoutInflater, container, false)
        datePicker = DatePicker(context)



        // Disable previous dates
        val today = Calendar.getInstance()
        binding.calendarView.minDate = today.timeInMillis // Disable all past dates

        // Automatically show current date and day name
        val currentDayName = SimpleDateFormat("EEEE", Locale.getDefault()).format(today.time) // Day name (e.g., Monday)
        val currentMonthName = SimpleDateFormat("MMMM", Locale.getDefault()).format(today.time) // Month name (e.g., November)
        val currentDayOfMonth = today.get(Calendar.DAY_OF_MONTH) // Day of the month (e.g., 30)

        // Set the buttons with the current date and day name
        binding.buttonDayName.text = "$currentDayName"  // Dynamic day name
        binding.buttonMonthDate.text = "$currentMonthName $currentDayOfMonth" // Dynamic month and date


        // Handle date selection
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // Get the selected date
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)

            // Format the selected date
            val dayName = SimpleDateFormat("EEEE", Locale.getDefault()).format(selectedDate.time) // Day name
            val monthName = SimpleDateFormat("MMMM", Locale.getDefault()).format(selectedDate.time) // Month name

            // Update button text dynamically
            binding.buttonDayName.text = "$monthName $dayOfMonth"
            if (dayName.equals("Saturday")){
                binding.buttonMonthDate.text = "Save for all Saturday"
            }else{
                binding.buttonMonthDate.text = "$dayName"
            }
            // Toast message for confirmation "$dayName"
            Toast.makeText(
                context,
                "Selected: $dayName, $monthName $dayOfMonth",
                Toast.LENGTH_SHORT
            ).show()
        }


    if (itemList.isEmpty()) {
            itemList.add(itemAdd(id = 1, name = "Item 2"))
        }
        // Set up LayoutManager
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = AddTimeAdapter(requireContext(), this, itemList)
        binding.recyclerView.adapter = adapter
        return binding.root
    }




    override fun onItemClicked(position: Int) {
        var list = itemAdd(id=1,"this")
        itemList.add(position + 1, list)
        adapter.notifyItemInserted(position + 1)
       // Toast.makeText(context, "this is Add",Toast.LENGTH_SHORT).show()
        adapter.notifyDataSetChanged()
    }

    // Handle item deletion
    override fun onItemDelete(position: Int) {
        itemList.removeAt(position)
        adapter.notifyItemRemoved(position)
        //Toast.makeText(context, "this is delete",Toast.LENGTH_SHORT).show()
        adapter.notifyItemRangeChanged(position, itemList.size) // Notify adapter about changes
        adapter.notifyDataSetChanged()
    }


}