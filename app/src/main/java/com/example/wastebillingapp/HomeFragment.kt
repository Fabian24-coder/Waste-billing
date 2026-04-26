package com.example.wastebillingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 1. Account Icon Click Handling
        view.findViewById<ImageView>(R.id.iv_profile).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, AccountFragment())
                .addToBackStack(null)
                .commit()
        }

        // 2. Pay Now button handling
        view.findViewById<Button>(R.id.btn_pay_now).setOnClickListener {
            val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNav?.selectedItemId = R.id.navigation_billing
        }

        // 3. Quick Actions - Request Extra Pickup
        // Corrected: Finding as View to avoid class cast and ensuring ID matches XML
        view.findViewById<View>(R.id.btn_request_pickup).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ExtraPickupFragment())
                .addToBackStack(null)
                .commit()
        }

        // 4. Quick Actions - Report Issue
        view.findViewById<View>(R.id.btn_report_issue).setOnClickListener {
            val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNav?.selectedItemId = R.id.navigation_support
        }

        // 5. Last Service Card Click
        view.findViewById<CardView>(R.id.card_service).setOnClickListener {
            showLastServiceDetails()
        }

        // 6. Setup Dynamic Data
        setupLastService(view)
        setupTrendChart(view)
        setupRecyclingTip(view)

        return view
    }

    private fun showLastServiceDetails() {
        val dialog = BottomSheetDialog(requireContext())
        val sheetView = layoutInflater.inflate(R.layout.bottom_sheet_pickup_details, null)
        
        val calendar = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -1) }
        val dateStr = SimpleDateFormat("MMM d, yyyy", Locale.getDefault()).format(calendar.time)
        
        sheetView.findViewById<TextView>(R.id.tv_detail_title).text = "Last Service Details"
        sheetView.findViewById<TextView>(R.id.tv_detail_status).text = "Completed"
        sheetView.findViewById<TextView>(R.id.tv_detail_status).setTextColor(resources.getColor(android.R.color.holo_green_dark, null))
        sheetView.findViewById<TextView>(R.id.tv_detail_date).text = dateStr
        sheetView.findViewById<TextView>(R.id.tv_detail_time).text = "08:15 AM"
        sheetView.findViewById<TextView>(R.id.tv_detail_truck).text = "TRUCK-UG-992"

        sheetView.findViewById<Button>(R.id.btn_close_details).setOnClickListener {
            dialog.dismiss()
        }

        dialog.setContentView(sheetView)
        dialog.show()
    }

    private fun setupLastService(view: View) {
        val tvServiceDesc = view.findViewById<TextView>(R.id.tv_service_desc)
        val calendar = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -1) }
        val dateStr = SimpleDateFormat("MMM d", Locale.getDefault()).format(calendar.time)
        val timeStr = SimpleDateFormat("h a", Locale.getDefault()).format(calendar.time)
        tvServiceDesc.text = "Picked up yesterday ($dateStr),\n$timeStr"
    }

    private fun setupRecyclingTip(view: View) {
        val tips = arrayOf(
            "Rinse your containers before recycling to avoid contamination.",
            "Keep plastics dry for better recycling. Moisture ruins the batch!",
            "Flatten your cardboard boxes to save space in your recycling bin.",
            "Check for the recycling symbol before tossing items in the bin.",
            "Never put electronics or batteries in your standard recycling bin."
        )
        val dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        view.findViewById<TextView>(R.id.tv_recycling_tip).text = tips[dayOfYear % tips.size]
    }

    private fun setupTrendChart(view: View) {
        val bars = listOf(
            view.findViewById<View>(R.id.bar1), view.findViewById<View>(R.id.bar2),
            view.findViewById<View>(R.id.bar3), view.findViewById<View>(R.id.bar4),
            view.findViewById<View>(R.id.bar5), view.findViewById<View>(R.id.bar6),
            view.findViewById<View>(R.id.bar7)
        )
        val trendData = listOf(40, 70, 50, 90, 60, 85, 45)
        bars.forEachIndexed { index, bar ->
            val params = bar.layoutParams as LinearLayout.LayoutParams
            params.height = dpToPx(trendData[index] * 1.5f)
            bar.layoutParams = params
        }
    }

    private fun dpToPx(dp: Float): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }
}