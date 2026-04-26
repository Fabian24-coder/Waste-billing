package com.example.wastebillingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
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
        val ivProfile = view.findViewById<ImageView>(R.id.iv_profile)
        ivProfile.setOnClickListener {
            // Programmatically select the Account item in the bottom navigation
            // This is safer than just replacing the fragment as it keeps the bottom nav state in sync
            val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            // Note: We don't have a specific account menu item in bottom nav based on previous menu file
            // If it's not in bottom nav, we just load the fragment manually
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, AccountFragment())
                .addToBackStack(null)
                .commit()
        }

        // 2. Pay Now button handling
        val btnPayNow = view.findViewById<Button>(R.id.btn_pay_now)
        btnPayNow.setOnClickListener {
            val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNav?.selectedItemId = R.id.navigation_billing
        }

        // 3. Dynamic Last Service Date
        setupLastService(view)

        // 4. Trend chart handling
        setupTrendChart(view)

        return view
    }

    private fun setupLastService(view: View) {
        val tvServiceDesc = view.findViewById<TextView>(R.id.tv_service_desc)
        
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1) // Set to yesterday
        
        val dateFormat = SimpleDateFormat("MMM d", Locale.getDefault())
        val timeFormat = SimpleDateFormat("h a", Locale.getDefault())
        
        val dateStr = dateFormat.format(calendar.time)
        val timeStr = timeFormat.format(calendar.time)
        
        tvServiceDesc.text = "Picked up yesterday ($dateStr),\n$timeStr"
    }

    private fun setupTrendChart(view: View) {
        val bars = listOf(
            view.findViewById<View>(R.id.bar1),
            view.findViewById<View>(R.id.bar2),
            view.findViewById<View>(R.id.bar3),
            view.findViewById<View>(R.id.bar4),
            view.findViewById<View>(R.id.bar5),
            view.findViewById<View>(R.id.bar6),
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