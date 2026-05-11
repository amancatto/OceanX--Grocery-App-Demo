package com.example.oceanxgroceryapp.data.model

/**
 * Domain / UI model combining a [Product] with its cart [quantity].
 * Used by the ViewModel and UI layers so they never depend on Room types.
 */
data class CartItem(
    val product: Product,
    val quantity: Int
)

// ---------- Mapper extensions ----------

/** Convert a domain [CartItem] to a Room [CartItemEntity] for persistence. */
fun CartItem.toEntity(): CartItemEntity = CartItemEntity(
    productId = product.id,
    productName = product.name,
    productPrice = product.price,
    productImageUrl = product.imageUrl,
    productCategory = product.category,
    quantity = quantity
)

/** Convert a Room [CartItemEntity] back to the domain [CartItem]. */
fun CartItemEntity.toCartItem(): CartItem = CartItem(
    product = Product(
        id = productId,
        name = productName,
        price = productPrice,
        imageUrl = productImageUrl,
        category = productCategory
    ),
    quantity = quantity
)
