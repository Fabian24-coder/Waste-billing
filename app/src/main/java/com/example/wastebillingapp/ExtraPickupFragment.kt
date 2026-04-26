package com.example.wastebillingapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ExtraPickupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_extra_pickup, container, false)

        val btnBack = view.findViewById<ImageView>(R.id.btn_back)
        val etDate = view.findViewById<EditText>(R.id.et_pickup_date)
        val etAddress = view.findViewById<EditText>(R.id.et_pickup_address)
        val spinnerType = view.findViewById<Spinner>(R.id.spinner_pickup_type)
        val btnSubmit = view.findViewById<Button>(R.id.btn_submit_request)

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        etDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, day)
                    val sdf = SimpleDateFormat("EEEE, MMM d, yyyy", Locale.getDefault())
                    etDate.setText(sdf.format(selectedDate.time))
                    etDate.error = null
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        btnSubmit.setOnClickListener {
            val date = etDate.text.toString()
            val address = etAddress.text.toString().trim()
            val wasteType = spinnerType.selectedItem.toString()

            var isValid = true

            if (date.isEmpty()) {
                etDate.error = "Please select a pickup date"
                isValid = false
            }

            if (address.isEmpty()) {
                etAddress.error = "Please enter the pickup address"
                isValid = false
            }

            if (isValid) {
                // Set flags to communicate with DashboardActivity and BillingFragment
                (activity as? DashboardActivity)?.isProgrammaticNav = true
                BillingFragment.isExtraPickupRequested = true
                
                // Successful Confirmation
                val message = "Extra pickup confirmed for $wasteType at $address on $date!"
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                
                // Return to dashboard
                parentFragmentManager.popBackStack()
                
                // Switch to Billing tab
                val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
                bottomNav?.selectedItemId = R.id.navigation_billing
            } else {
                Toast.makeText(context, "Please provide all required details", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}