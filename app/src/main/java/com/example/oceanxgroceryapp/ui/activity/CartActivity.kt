package com.example.oceanxgroceryapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.oceanxgroceryapp.data.local.GroceryDatabase
import com.example.oceanxgroceryapp.data.repository.CartRepository
import com.example.oceanxgroceryapp.databinding.ActivityCartBinding
import com.example.oceanxgroceryapp.ui.adapter.CartAdapter
import com.example.oceanxgroceryapp.ui.viewmodel.CartViewModel
import com.example.oceanxgroceryapp.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private val viewModel: CartViewModel by viewModels {
        val database = GroceryDatabase.getInstance(this)
        ViewModelFactory(CartRepository(database.cartDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCartList()
        observeSummary()

        binding.btnCheckout.setOnClickListener {
            if (viewModel.cartItems.value.isNotEmpty()) {
                startActivity(Intent(this, CheckoutActivity::class.java))
            } else {
                Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupCartList() {
        val adapter = CartAdapter(
            onIncrease = { viewModel.updateQuantity(it.product.id, it.quantity + 1) },
            onDecrease = { viewModel.updateQuantity(it.product.id, it.quantity - 1) },
            onRemove = { viewModel.removeItem(it.product.id) }
        )
        binding.rvCartItems.adapter = adapter

        lifecycleScope.launch {
            viewModel.cartItems.collect { 
                adapter.submitList(it)
                binding.tvItemCount.text = "${it.size} Items"
            }
        }
    }

    private fun observeSummary() {
        lifecycleScope.launch {
            viewModel.subtotal.collect { binding.tvSubtotal.text = "$$it" }
        }
        lifecycleScope.launch {
            viewModel.total.collect { 
                binding.tvTotal.text = "$$it"
                binding.tvTotalBottom.text = "$$it"
            }
        }
        binding.tvFee.text = if (viewModel.deliveryFee == 0.0) "FREE" else "$${viewModel.deliveryFee}"
    }
}
