package com.example.wastebillingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.wastebillingapp.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityDashboardBinding
    
    // Flag to track if navigation was triggered programmatically (e.g. from Extra Pickup)
    var isProgrammaticNav: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set default fragment
        loadFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.navigation_schedule -> {
                    loadFragment(ScheduleFragment())
                    true
                }
                R.id.navigation_billing -> {
                    // Only reset the flag if it was a direct user click on the tab
                    if (!isProgrammaticNav) {
                        BillingFragment.isExtraPickupRequested = false
                    }
                    // Reset our internal tracking flag for the next interaction
                    isProgrammaticNav = false

                    loadFragment(BillingFragment())
                    true
                }
                R.id.navigation_support -> {
                    loadFragment(SupportFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .commit()
    }
}