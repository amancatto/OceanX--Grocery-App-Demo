package com.example.oceanxgroceryapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.oceanxgroceryapp.R
import com.example.oceanxgroceryapp.data.model.CartItem

class CartAdapter(
    private val onIncrease: (CartItem) -> Unit,
    private val onDecrease: (CartItem) -> Unit,
    private val onRemove: (CartItem) -> Unit
) : ListAdapter<CartItem, CartAdapter.CartViewHolder>(CartDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivProduct: ImageView = itemView.findViewById(R.id.ivProduct)
        private val tvName: TextView = itemView.findViewById(R.id.tvProductName)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        private val tvQty: TextView = itemView.findViewById(R.id.tvQuantity)
        private val btnPlus: View = itemView.findViewById(R.id.btnPlus)
        private val btnMinus: View = itemView.findViewById(R.id.btnMinus)
        private val btnRemove: View = itemView.findViewById(R.id.btnRemove)

        fun bind(item: CartItem) {
            tvName.text = item.product.name
            tvPrice.text = "$${String.format("%.2f", item.product.price * item.quantity)}"
            tvQty.text = item.quantity.toString()
            
            ivProduct.setImageResource(android.R.drawable.ic_menu_report_image)

            btnPlus.setOnClickListener { onIncrease(item) }
            btnMinus.setOnClickListener { onDecrease(item) }
            btnRemove.setOnClickListener { onRemove(item) }
        }
    }

    class CartDiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean = 
            oldItem.product.id == newItem.product.id
        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean = 
            oldItem == newItem
    }
}
