package com.example.oceanxgroceryapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oceanxgroceryapp.data.local.GroceryDatabase
import com.example.oceanxgroceryapp.data.model.Product
import com.example.oceanxgroceryapp.data.model.Category
import com.example.oceanxgroceryapp.data.repository.CartRepository
import com.example.oceanxgroceryapp.data.repository.ProductRepository
import com.example.oceanxgroceryapp.databinding.ActivityHomeBinding
import com.example.oceanxgroceryapp.ui.adapter.CategoryAdapter
import com.example.oceanxgroceryapp.ui.adapter.ProductAdapter
import com.example.oceanxgroceryapp.ui.adapter.SpotlightAdapter
import com.example.oceanxgroceryapp.ui.viewmodel.HomeViewModel
import com.example.oceanxgroceryapp.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels {
        val database = GroceryDatabase.getInstance(this)
        ViewModelFactory(CartRepository(database.cartDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCategories()
        setupSpotlight()
        setupProducts()
        setupSearch()
        observeCart()
        setupNavigation()

        binding.layoutCartSummary.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun setupNavigation() {
        binding.bottomNavigation.navProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        binding.btnProfileHeader.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        binding.bottomNavigation.navCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
        // Highlight Home
        binding.bottomNavigation.navHome.alpha = 1.0f
    }

    private fun setupCategories() {
        val categories = ProductRepository.getCategoryList()
        val adapter = CategoryAdapter(categories) { categoryName ->
            viewModel.filterByCategory(categoryName)
        }
        binding.rvCategories.adapter = adapter
    }

    private fun setupSpotlight() {
        val adapter = SpotlightAdapter { product ->
            viewModel.addToCart(product)
        }
        binding.rvSpotlight.adapter = adapter
        // Show first 5 products in spotlight
        adapter.submitList(ProductRepository.getAllProducts().take(5))
    }

    private fun setupProducts() {
        val adapter = ProductAdapter { product ->
            viewModel.addToCart(product)
        }
        binding.rvProducts.apply {
            this.adapter = adapter
            this.layoutManager = GridLayoutManager(this@HomeActivity, 2)
        }

        lifecycleScope.launch {
            viewModel.products.collect { list: List<Product> -> adapter.submitList(list) }
        }
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener {
            viewModel.search(it.toString())
        }
    }

    private fun observeCart() {
        lifecycleScope.launch {
            viewModel.cartItemCount.collect { count: Int ->
                if (count > 0) {
                    binding.layoutCartSummary.visibility = View.VISIBLE
                    binding.tvCartCount.text = "$count Items"
                } else {
                    binding.layoutCartSummary.visibility = View.GONE
                }
            }
        }
        lifecycleScope.launch {
            viewModel.cartTotal.collect { total: Double ->
                binding.tvCartTotal.text = "$${String.format("%.2f", total)}"
            }
        }
    }
}
