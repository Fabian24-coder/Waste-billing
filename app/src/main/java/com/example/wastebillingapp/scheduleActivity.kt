package com.example.wastebillingapp

import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        val calendar = findViewById<CalendarView>(R.id.calendarView)
        val btnConfirm = findViewById<Button>(R.id.btn_confirm_schedule)
        var selectedDate = ""

        calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
        }

        btnConfirm.setOnClickListener {
            if (selectedDate.isNotEmpty()) {
                Toast.makeText(this, "Pickup scheduled for: $selectedDate", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Please select a date first", Toast.LENGTH_SHORT).show()
            }
        }
    }
}