package com.example.wastebillingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val emailField = findViewById<EditText>(R.id.et_login_email)
        val passwordField = findViewById<EditText>(R.id.et_login_password)
        val signInBtn = findViewById<Button>(R.id.btn_signin)

        signInBtn.setOnClickListener {
            val phone = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both phone and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simple mock login for first functional app
            if (phone == "1234567890" && password == "password") {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid phone or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}