package com.example.wastebillingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.switchmaterial.SwitchMaterial

class ScheduleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        // Functional Back Arrow
        view.findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNav?.selectedItemId = R.id.navigation_home
        }

        // Functional Calendar
        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = "$dayOfMonth/${month + 1}/$year"
            Toast.makeText(context, "Selected date: $date", Toast.LENGTH_SHORT).show()
        }

        // Missed Pickup Switch
        val missedPickupSwitch = view.findViewById<SwitchMaterial>(R.id.switch_missed_pickup)
        missedPickupSwitch.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Missed pickup reported" else "Report cancelled"
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        // View Details Text
        view.findViewById<TextView>(R.id.tv_view_details).setOnClickListener {
            Toast.makeText(context, "Opening plan details...", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}