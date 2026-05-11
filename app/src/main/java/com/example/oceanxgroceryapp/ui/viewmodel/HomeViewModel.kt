package com.example.oceanxgroceryapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oceanxgroceryapp.data.model.Product
import com.example.oceanxgroceryapp.data.model.CartItem
import com.example.oceanxgroceryapp.data.repository.CartRepository
import com.example.oceanxgroceryapp.data.repository.ProductRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val cartRepository: CartRepository) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(ProductRepository.getAllProducts())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    // Observable cart summary
    private val cartItems: Flow<List<CartItem>> = cartRepository.cartItems
    
    val cartItemCount: StateFlow<Int> = cartItems.map { list: List<CartItem> -> 
        list.sumOf { item: CartItem -> item.quantity } 
    }.stateIn(viewModelScope, SharingStarted.Lazily, 0)
        
    val cartTotal: StateFlow<Double> = cartItems.map { list: List<CartItem> -> 
        list.sumOf { item: CartItem -> item.product.price * item.quantity } 
    }.stateIn(viewModelScope, SharingStarted.Lazily, 0.0)

    fun search(query: String) {
        _products.value = if (query.isEmpty()) {
            ProductRepository.getAllProducts()
        } else {
            ProductRepository.searchProducts(query)
        }
    }

    fun filterByCategory(category: String) {
        _products.value = ProductRepository.getProductsByCategory(category)
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            cartRepository.addToCart(product)
        }
    }
}
