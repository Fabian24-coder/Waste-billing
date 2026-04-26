package com.example.wastebillingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class BillingFragment : Fragment() {

    companion object {
        // Global flag to track extra pickup requests (Real scenario simulation)
        var isExtraPickupRequested: Boolean = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_billing, container, false)

        // Back Arrow
        view.findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNav?.selectedItemId = R.id.navigation_home
        }

        setupBillingDetails(view)

        // Show/Hide Extra Pickup Amount
        val cardExtraPickup = view.findViewById<CardView>(R.id.card_extra_pickup_due)
        if (isExtraPickupRequested) {
            cardExtraPickup.visibility = View.VISIBLE
        } else {
            cardExtraPickup.visibility = View.GONE
        }

        val etMomoNumber = view.findViewById<EditText>(R.id.et_momo_number)
        val btnPayMomo = view.findViewById<Button>(R.id.btn_pay_momo)
        
        btnPayMomo.setOnClickListener {
            val input = etMomoNumber.text.toString().trim()
            if (input.length == 9) {
                val fullNumber = "+256 $input"
                Toast.makeText(context, "Redirecting to Mobile Money for $fullNumber...", Toast.LENGTH_SHORT).show()
                
                // Clear the extra pickup flag after payment attempt
                if (isExtraPickupRequested) {
                    isExtraPickupRequested = false
                    cardExtraPickup.visibility = View.GONE
                }
            } else {
                etMomoNumber.error = "Please enter 9 digits"
            }
        }

        return view
    }

    private fun setupBillingDetails(view: View) {
        val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
        val calendar = Calendar.getInstance()

        calendar.add(Calendar.MONTH, 1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        view.findViewById<TextView>(R.id.tv_upcoming_due).text = "${dateFormat.format(calendar.time)} | Amount: UGX 15,000"

        calendar.add(Calendar.MONTH, -1)
        calendar.set(Calendar.DAY_OF_MONTH, 15)
        view.findViewById<TextView>(R.id.tv_date1).text = dateFormat.format(calendar.time)

        calendar.add(Calendar.MONTH, -1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        view.findViewById<TextView>(R.id.tv_date2).text = dateFormat.format(calendar.time)
    }
}