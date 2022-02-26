package com.android.feature_main.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.common.extensions.setImage
import com.android.feature_main.R
import com.android.feature_main.databinding.ItemCategoryBinding
import com.android.feature_main.domain.model.HomeStore

class CategoryAdapter(
    private val listener: Listener
) : GenericAdapter<String, ItemCategoryBinding>() {

    interface Listener {
        fun onItemClick(item: String)
    }

    private val categories = listOf("Phones", "Computers", "Health", "Books")
    private var selected = categories[0]

    init {
        submitList(categories)
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemCategoryBinding {
        return ItemCategoryBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemCategoryBinding, itemView: View, item: String, position: Int) {

        val icon = when (item) {
            categories[1] -> R.drawable.ic_computers
            categories[2] -> R.drawable.ic_health
            categories[3] -> R.drawable.ic_books
            else -> R.drawable.ic_phones
        }

        with(binding) {
            categoryTitle.text = item
            categoryImage.setImage(icon)
            categoryTitle.isSelected = item == selected
            categoryImage.isSelected = item == selected

            categoryImage.setOnClickListener {
                listener.onItemClick(item)
                val oldSelected = categories.indexOf(selected)
                selected = item
                notifyItemChanged(position)
                notifyItemChanged(oldSelected)
            }
        }

        itemView.setOnClickListener { listener.onItemClick(item) }
    }
}