package com.example.wastebillingapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class AccountFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        // 1. Back Arrow Functionality
        view.findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNav?.selectedItemId = R.id.navigation_home
        }

        // 2. Logout Functionality
        view.findViewById<TextView>(R.id.btn_logout).setOnClickListener {
            Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        // 3. Menu Item Feedbacks
        view.findViewById<TextView>(R.id.btn_payment_methods).setOnClickListener {
            Toast.makeText(context, "Redirecting to Payment Methods...", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<TextView>(R.id.btn_settings).setOnClickListener {
            Toast.makeText(context, "Opening App Settings...", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}