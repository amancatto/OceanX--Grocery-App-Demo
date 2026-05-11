package com.example.oceanxgroceryapp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.oceanxgroceryapp.data.local.GroceryDatabase
import com.example.oceanxgroceryapp.data.repository.CartRepository
import com.example.oceanxgroceryapp.databinding.ActivityCheckoutBinding
import com.example.oceanxgroceryapp.ui.viewmodel.CartViewModel
import com.example.oceanxgroceryapp.ui.viewmodel.ViewModelFactory

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding
    private val viewModel: CartViewModel by viewModels {
        val database = GroceryDatabase.getInstance(this)
        ViewModelFactory(CartRepository(database.cartDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnPlaceOrder.setOnClickListener {
            viewModel.clearCart()
            startActivity(Intent(this, OrderSuccessActivity::class.java))
            finish()
        }
    }
}
