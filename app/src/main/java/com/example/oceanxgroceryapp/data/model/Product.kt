package com.example.oceanxgroceryapp.data.model

/**
 * Represents a grocery product available in the store.
 * This is a plain data class (not a Room Entity) because products
 * come from a hardcoded / remote source, not from the local DB.
 */
data class Product(
    val id: Int,
    val name: String,
    val price: Double,          // price per unit in ₹
    val imageUrl: String,       // drawable resource name or URL
    val category: String,       // e.g. "Fruits", "Vegetables", "Dairy"
    val weight: String = ""
)
