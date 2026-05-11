package com.example.oceanxgroceryapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room Entity that persists a product and its quantity in the local cart DB.
 *
 * We flatten the Product fields directly into this table to avoid a foreign-key
 * join every time the cart is loaded, keeping reads simple and fast.
 */
@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey
    val productId: Int,
    val productName: String,
    val productPrice: Double,
    val productImageUrl: String,
    val productCategory: String,
    val quantity: Int = 1
)
