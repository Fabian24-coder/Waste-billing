package com.example.wastebillingapp

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SupportFragment : Fragment() {

    private var capturedPhoto: Bitmap? = null
    private var tvPhotoLabelRef: TextView? = null
    private var ivPhotoRef: ImageView? = null

    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) {
            capturedPhoto = bitmap
            tvPhotoLabelRef?.text = "Photo attached successfully"
            tvPhotoLabelRef?.setTextColor(resources.getColor(android.R.color.holo_green_dark, null))
            ivPhotoRef?.setImageBitmap(bitmap)
            ivPhotoRef?.setColorFilter(0) // Remove teal tint to show actual photo
            Toast.makeText(context, "Image captured!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "No image captured", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_support, container, false)

        // 1. Back Arrow Functionality
        view.findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNav?.selectedItemId = R.id.navigation_home
        }

        // 2. FAB - Add New Issue
        view.findViewById<FloatingActionButton>(R.id.fab_add_issue).setOnClickListener {
            showAddIssueSheet()
        }

        // 3. Existing Support Options
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

    private fun showAddIssueSheet() {
        val dialog = BottomSheetDialog(requireContext())
        val sheetView = layoutInflater.inflate(R.layout.bottom_sheet_add_issue, null)

        val etDescription = sheetView.findViewById<EditText>(R.id.et_issue_description)
        val btnSubmit = sheetView.findViewById<Button>(R.id.btn_submit_issue)
        val btnTakePhoto = sheetView.findViewById<LinearLayout>(R.id.btn_take_photo)
        tvPhotoLabelRef = sheetView.findViewById<TextView>(R.id.tv_photo_label)
        ivPhotoRef = sheetView.findViewById<ImageView>(R.id.iv_issue_photo)

        // Trigger real camera
        btnTakePhoto.setOnClickListener {
            Toast.makeText(context, "Connecting to camera...", Toast.LENGTH_SHORT).show()
            takePictureLauncher.launch(null)
        }

        btnSubmit.setOnClickListener {
            val description = etDescription.text.toString().trim()
            if (description.isEmpty()) {
                etDescription.error = "Please describe your issue"
            } else {
                val hasPhoto = if (capturedPhoto != null) "with attachment" else "without attachment"
                Toast.makeText(context, "Support ticket created $hasPhoto!", Toast.LENGTH_LONG).show()
                capturedPhoto = null // Reset for next time
                dialog.dismiss()
            }
        }

        dialog.setContentView(sheetView)
        dialog.show()
    }
}