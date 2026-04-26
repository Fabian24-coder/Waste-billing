package com.example.wastebillingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.switchmaterial.SwitchMaterial

class NotificationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        // Back Arrow Functionality - returns to Account
        view.findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Notification management logic (Real scenario simulation)
        val switchBilling = view.findViewById<SwitchMaterial>(R.id.switch_billing)
        val switchSchedule = view.findViewById<SwitchMaterial>(R.id.switch_schedule)
        val switchSupport = view.findViewById<SwitchMaterial>(R.id.switch_support)

        switchBilling.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "enabled" else "disabled"
            Toast.makeText(context, "Billing alerts $status", Toast.LENGTH_SHORT).show()
        }

        switchSchedule.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "enabled" else "disabled"
            Toast.makeText(context, "Schedule reminders $status", Toast.LENGTH_SHORT).show()
        }

        switchSupport.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "enabled" else "disabled"
            Toast.makeText(context, "Support updates $status", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}