package com.example.wastebillingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class SupportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_support, container, false)

        view.findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            // Navigate back to Home tab
            val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNav?.selectedItemId = R.id.navigation_home
        }

        view.findViewById<LinearLayout>(R.id.btn_faq).setOnClickListener {
            Toast.makeText(context, "Opening FAQs...", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<LinearLayout>(R.id.btn_chat).setOnClickListener {
            Toast.makeText(context, "Connecting to Live Chat...", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<LinearLayout>(R.id.btn_call).setOnClickListener {
            Toast.makeText(context, "Initiating call to Support...", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btn_report_missed).setOnClickListener {
            Toast.makeText(context, "Report submitted successfully!", Toast.LENGTH_LONG).show()
        }

        return view
    }
}