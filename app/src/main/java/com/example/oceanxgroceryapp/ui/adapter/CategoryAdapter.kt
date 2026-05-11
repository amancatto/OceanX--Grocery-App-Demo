package com.example.oceanxgroceryapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oceanxgroceryapp.R
import com.example.oceanxgroceryapp.data.model.Category

class CategoryAdapter(
    private val categories: List<Category>,
    private val onCategoryClick: (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position], position == selectedPosition)
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvCategoryName)
        private val ivIcon: ImageView = itemView.findViewById(R.id.ivCategoryIcon)

        fun bind(category: Category, isSelected: Boolean) {
            tvName.text = category.name
            
            // Set icon
            val resId = itemView.context.resources.getIdentifier(
                category.iconRes, "drawable", itemView.context.packageName
            )
            if (resId != 0) {
                ivIcon.setImageResource(resId)
            } else {
                ivIcon.setImageResource(android.R.drawable.ic_menu_help)
            }

            itemView.alpha = if (isSelected) 1.0f else 0.6f
            
            itemView.setOnClickListener {
                val oldPos = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(oldPos)
                notifyItemChanged(selectedPosition)
                onCategoryClick(category.name)
            }
        }
    }
}
