package com.example.oceanxgroceryapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.oceanxgroceryapp.data.model.CartItemEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for all cart-related Room operations.
 *
 * All suspend / Flow functions are safe to call from a coroutine on any dispatcher;
 * Room moves the actual DB work to its internal IO thread automatically.
 */
@Dao
interface CartDao {

    /** Observe the full cart in real-time. Emits a new list whenever any row changes. */
    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): Flow<List<CartItemEntity>>

    /**
     * Insert a new cart item.
     * [OnConflictStrategy.REPLACE] means that if the product is already in the
     * cart the entire row (including quantity) will be overwritten.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItemEntity)

    /** Update only the quantity for an existing cart item identified by [productId]. */
    @Query("UPDATE cart_items SET quantity = :quantity WHERE productId = :productId")
    suspend fun updateQuantity(productId: Int, quantity: Int)

    /** Remove a specific product from the cart. */
    @Query("DELETE FROM cart_items WHERE productId = :productId")
    suspend fun deleteCartItem(productId: Int)

    /** Wipe the entire cart (used after a successful order placement). */
    @Query("DELETE FROM cart_items")
    suspend fun clearCart()

    /** One-shot fetch of a single cart row – useful for quantity checks. */
    @Query("SELECT * FROM cart_items WHERE productId = :productId")
    suspend fun getCartItemById(productId: Int): CartItemEntity?
}
