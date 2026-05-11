package com.example.oceanxgroceryapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.oceanxgroceryapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupOtpForwarding()

        binding.btnVerify.setOnClickListener {
            val otp = "${binding.otp1.text}${binding.otp2.text}${binding.otp3.text}${binding.otp4.text}"
            if (otp == "1234") {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid OTP. Use 1234", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupOtpForwarding() {
        binding.otp1.doAfterTextChanged { if (it?.length == 1) binding.otp2.requestFocus() }
        binding.otp2.doAfterTextChanged { if (it?.length == 1) binding.otp3.requestFocus() }
        binding.otp3.doAfterTextChanged { if (it?.length == 1) binding.otp4.requestFocus() }
    }
}
