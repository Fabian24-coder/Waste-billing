package com.example.wastebillingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // 1. Back Arrow Functionality
        view.findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // 2. Theme/Appearance (Dark Mode)
        val switchDarkMode = view.findViewById<SwitchMaterial>(R.id.switch_dark_mode)
        switchDarkMode.isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Toast.makeText(context, "Dark Mode Enabled", Toast.LENGTH_SHORT).show()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Toast.makeText(context, "Light Mode Enabled", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. Font Size Simulation
        val btnFontSize = view.findViewById<LinearLayout>(R.id.btn_font_size)
        val tvCurrentFontSize = view.findViewById<TextView>(R.id.tv_current_font_size)
        btnFontSize.setOnClickListener {
            val sizes = arrayOf("Small", "Medium (Default)", "Large", "Extra Large")
            AlertDialog.Builder(requireContext())
                .setTitle("Select Font Size")
                .setItems(sizes) { _, which ->
                    tvCurrentFontSize.text = sizes[which]
                    Toast.makeText(context, "Font size set to ${sizes[which]}", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        // 4. Language Selection Simulation
        val btnLanguage = view.findViewById<LinearLayout>(R.id.btn_language)
        val tvCurrentLanguage = view.findViewById<TextView>(R.id.tv_current_language)
        btnLanguage.setOnClickListener {
            val languages = arrayOf("English", "Luganda", "Swahili", "French")
            AlertDialog.Builder(requireContext())
                .setTitle("Select Language")
                .setItems(languages) { _, which ->
                    tvCurrentLanguage.text = languages[which]
                    Toast.makeText(context, "Language changed to ${languages[which]}", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        return view
    }
}