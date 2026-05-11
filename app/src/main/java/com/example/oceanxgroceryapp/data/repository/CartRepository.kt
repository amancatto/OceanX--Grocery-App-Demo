package com.example.oceanxgroceryapp.data.repository

import com.example.oceanxgroceryapp.data.local.CartDao
import com.example.oceanxgroceryapp.data.model.CartItem
import com.example.oceanxgroceryapp.data.model.CartItemEntity
import com.example.oceanxgroceryapp.data.model.Product
import com.example.oceanxgroceryapp.data.model.toCartItem
import com.example.oceanxgroceryapp.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * CartRepository is the single source of truth for cart data.
 *
 * It mediates between [CartDao] (Room) and the ViewModel layer,
 * mapping Room entities ↔ domain models so that no Room types
 * leak into the UI or ViewModel.
 */
class CartRepository(private val cartDao: CartDao) {

    /**
     * Reactive stream of all cart items.
     * Collect this in your ViewModel with [viewModelScope] and expose it
     * as LiveData / StateFlow for the UI.
     */
    val cartItems: Flow<List<CartItem>> = cartDao.getAllCartItems()
        .map { entities -> entities.map { it.toCartItem() } }

    /**
     * Add a product to the cart or update its quantity if already present.
     *
     * @param product  the product to add
     * @param quantity desired quantity (default 1)
     */
    suspend fun addToCart(product: Product, quantity: Int = 1) {
        val existing = cartDao.getCartItemById(product.id)
        if (existing != null) {
            cartDao.updateQuantity(product.id, existing.quantity + quantity)
        } else {
            val entity = CartItem(product, quantity).toEntity()
            cartDao.insertCartItem(entity)
        }
    }

    /**
     * Explicitly set the quantity for a cart item.
     * If [quantity] drops to 0 or below the item is removed from the cart.
     */
    suspend fun updateQuantity(productId: Int, quantity: Int) {
        if (quantity <= 0) {
            cartDao.deleteCartItem(productId)
        } else {
            cartDao.updateQuantity(productId, quantity)
        }
    }

    /** Remove a specific item from the cart. */
    suspend fun removeFromCart(productId: Int) {
        cartDao.deleteCartItem(productId)
    }

    /** Clear all items – call after a successful order placement. */
    suspend fun clearCart() {
        cartDao.clearCart()
    }
}
