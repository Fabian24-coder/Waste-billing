package com.example.wastebillingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.switchmaterial.SwitchMaterial
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ScheduleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        val tvSelectedDateLabel = view.findViewById<TextView>(R.id.tv_selected_date_label)
        val btnReschedule = view.findViewById<Button>(R.id.btn_reschedule)

        // Functional Back Arrow
        view.findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNav?.selectedItemId = R.id.navigation_home
        }

        // Functional Calendar with dynamic details update
        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            val dateFormat = SimpleDateFormat("EEEE, MMM d, yyyy", Locale.getDefault())
            val dateStr = dateFormat.format(calendar.time)
            
            tvSelectedDateLabel.text = "Selected Date: $dateStr"
            Toast.makeText(context, "Checking schedule for $dateStr", Toast.LENGTH_SHORT).show()
        }

        // Functional Reschedule Button
        btnReschedule.setOnClickListener {
            Toast.makeText(context, "Reschedule request sent for processing", Toast.LENGTH_LONG).show()
        }

        // Missed Pickup Switch
        val missedPickupSwitch = view.findViewById<SwitchMaterial>(R.id.switch_missed_pickup)
        missedPickupSwitch.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Missed pickup reported" else "Report cancelled"
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        // View Details Text - Show Bottom Sheet
        view.findViewById<TextView>(R.id.tv_view_details).setOnClickListener {
            showPlanDetails()
        }

        return view
    }

    private fun showPlanDetails() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_plan_details, null)
        
        view.findViewById<Button>(R.id.btn_close_details).setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
}