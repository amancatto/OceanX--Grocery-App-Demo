package com.example.oceanxgroceryapp.data.repository

import com.example.oceanxgroceryapp.data.model.Product
import com.example.oceanxgroceryapp.data.model.Category

object ProductRepository {

    private val categories = listOf(
        Category("All", "ic_all"),
        Category("Fruits", "cat_fruits"),
        Category("Vegetables", "cat_veg"),
        Category("Dairy", "cat_dairy"),
        Category("Bakery", "cat_dairy"),
        Category("Snacks", "cat_snacks"),
        Category("Beverages", "cat_snacks")
    )

    private val products = listOf(
        // ── Fruits ──────────────────────────────────────────────────────────
        Product(1, "Bananas", 1.99, "ic_banana", "Fruits", "1 dozen"),
        Product(2, "Apples – Shimla", 2.50, "ic_apple", "Fruits", "500 g"),
        Product(3, "Mangoes – Alphonso", 5.99, "ic_mango", "Fruits", "4 pcs"),
        Product(4, "Strawberries", 3.99, "ic_strawberry", "Fruits", "250 g"),
        Product(13, "Grapes – Black", 2.99, "ic_grapes", "Fruits", "500 g"),
        Product(14, "Watermelon", 4.50, "ic_watermelon", "Fruits", "1 pc"),

        // ── Vegetables ──────────────────────────────────────────────────────
        Product(5, "Tomatoes", 0.99, "ic_tomato", "Vegetables", "500 g"),
        Product(6, "Onions", 1.50, "ic_onion", "Vegetables", "1 kg"),
        Product(7, "Potatoes", 1.25, "ic_potato", "Vegetables", "1 kg"),
        Product(8, "Spinach", 0.75, "ic_spinach", "Vegetables", "1 bunch"),
        Product(15, "Broccoli", 2.20, "ic_broccoli", "Vegetables", "1 pc"),
        Product(16, "Bell Peppers", 1.80, "ic_peppers", "Vegetables", "3 pcs"),
        Product(17, "Carrots", 1.10, "ic_carrot", "Vegetables", "500 g"),

        // ── Dairy & Bakery ──────────────────────────────────────────────────
        Product(9, "Full-Cream Milk", 2.10, "ic_milk", "Dairy", "1 L"),
        Product(10, "Paneer", 3.50, "ic_paneer", "Dairy", "200 g"),
        Product(11, "Greek Yoghurt", 1.99, "ic_curd", "Dairy", "400 g"),
        Product(12, "Butter – Salted", 2.25, "ic_butter", "Dairy", "100 g"),
        Product(18, "Whole Wheat Bread", 1.50, "ic_bread", "Bakery", "1 loaf"),
        Product(19, "Eggs – Free Range", 4.99, "ic_eggs", "Dairy", "12 pcs"),
        Product(20, "Cheddar Cheese", 5.50, "ic_cheese", "Dairy", "200 g"),

        // ── Snacks & Drinks ─────────────────────────────────────────────────
        Product(21, "Potato Chips", 1.50, "ic_chips", "Snacks", "150 g"),
        Product(22, "Chocolate Cookies", 2.99, "ic_cookies", "Snacks", "200 g"),
        Product(23, "Orange Juice", 3.99, "ic_juice", "Beverages", "1 L"),
        Product(24, "Cold Brew Coffee", 4.50, "ic_coffee", "Beverages", "500 ml"),
        Product(25, "Mixed Nuts", 6.99, "ic_nuts", "Snacks", "250 g"),
        Product(26, "Sparkling Water", 1.25, "ic_water", "Beverages", "750 ml")
    )

    fun getAllProducts(): List<Product> = products

    fun getProductsByCategory(category: String?): List<Product> =
        if (category.isNullOrBlank() || category == "All") products
        else products.filter { it.category.equals(category, ignoreCase = true) }

    fun getCategoryList(): List<Category> = categories

    fun searchProducts(query: String): List<Product> =
        products.filter { it.name.contains(query, ignoreCase = true) }
}
