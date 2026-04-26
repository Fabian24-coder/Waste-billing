package com.example.wastebillingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the REGISTER button on the home screen
        val registerBtn = findViewById<Button>(R.id.btn_main_register)
        
        val loginBtn = findViewById<Button>(R.id.btn_main_login)
        loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Set action for when the button is clicked
        registerBtn.setOnClickListener {
            // Create intent to open RegistrationActivity
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}