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
import com.example.oceanxgroceryapp.data.model.Product

class SpotlightAdapter(
    private val onAddClick: (Product) -> Unit
) : ListAdapter<Product, SpotlightAdapter.SpotlightViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotlightViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_large, parent, false)
        return SpotlightViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpotlightViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SpotlightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivProduct: ImageView = itemView.findViewById(R.id.ivProduct)
        private val tvName: TextView = itemView.findViewById(R.id.tvProductName)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        private val tvWeight: TextView = itemView.findViewById(R.id.tvProductWeight)
        private val btnAdd: View = itemView.findViewById(R.id.btnAddToCart)

        fun bind(product: Product) {
            tvName.text = product.name
            tvWeight.text = product.weight
            tvPrice.text = "$${String.format("%.2f", product.price)}"
            
            val resId = itemView.context.resources.getIdentifier(
                product.imageUrl, "drawable", itemView.context.packageName
            )
            if (resId != 0) {
                ivProduct.setImageResource(resId)
            } else {
                ivProduct.setImageResource(android.R.drawable.ic_menu_gallery)
            }

            btnAdd.setOnClickListener { onAddClick(product) }
        }
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem
    }
}
