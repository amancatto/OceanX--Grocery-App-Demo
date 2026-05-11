package com.example.oceanxgroceryapp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.oceanxgroceryapp.databinding.ActivityProfileBinding
import com.example.oceanxgroceryapp.databinding.ItemProfileMenuBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMenuItems()
        setupBottomNav()
    }

    private fun setupMenuItems() {
        // My Orders
        ItemProfileMenuBinding.bind(binding.itemOrders.root).apply {
            tvMenuTitle.text = "My Orders"
            tvMenuSubtitle.text = "View order history"
            ivMenuIcon.setImageResource(android.R.drawable.ic_menu_agenda)
        }

        // Saved Addresses
        ItemProfileMenuBinding.bind(binding.itemAddresses.root).apply {
            tvMenuTitle.text = "Saved Addresses"
            tvMenuSubtitle.text = "Manage delivery locations"
            ivMenuIcon.setImageResource(android.R.drawable.ic_menu_mylocation)
        }

        // Payment Methods
        ItemProfileMenuBinding.bind(binding.itemPayments.root).apply {
            tvMenuTitle.text = "Payment Methods"
            tvMenuSubtitle.text = "Manage cards and UPI"
            ivMenuIcon.setImageResource(android.R.drawable.ic_menu_save)
        }

        // Notifications
        ItemProfileMenuBinding.bind(binding.itemNotifications.root).apply {
            tvMenuTitle.text = "Notifications"
            tvMenuSubtitle.text = "Push notification settings"
            ivMenuIcon.setImageResource(android.R.drawable.ic_popup_reminder)
        }

        // Help & Support
        ItemProfileMenuBinding.bind(binding.itemHelp.root).apply {
            tvMenuTitle.text = "Help & Support"
            tvMenuSubtitle.text = "FAQs and customer care"
            ivMenuIcon.setImageResource(android.R.drawable.ic_menu_help)
        }

        // Logout
        ItemProfileMenuBinding.bind(binding.itemLogout.root).apply {
            tvMenuTitle.text = "Logout"
            tvMenuSubtitle.text = "Sign out of your account"
            ivMenuIcon.setImageResource(android.R.drawable.ic_lock_power_off)
            tvMenuTitle.setTextColor(android.graphics.Color.RED)
            ivIconContainer.setCardBackgroundColor(android.graphics.Color.parseColor("#FFF0F0"))
            ivMenuIcon.setColorFilter(android.graphics.Color.RED)
        }
    }

    private fun setupBottomNav() {
        binding.bottomNavigation.navHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        binding.bottomNavigation.navCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
        // Current is Profile
        binding.bottomNavigation.navProfile.alpha = 1.0f
    }
}
