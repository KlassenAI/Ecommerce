package com.android.feature_main.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.android.feature_main.R
import com.android.feature_main.databinding.ItemCategoryBinding
import com.android.feature_main.domain.model.CategoryRadio

class CategoryAdapter(
    private val listener: Listener
) : GenericAdapter<CategoryRadio, ItemCategoryBinding>() {

    interface Listener {
        fun setCheckedItem(position: Int)
    }

    init {
        submitList(
            listOf(
                CategoryRadio(R.drawable.ic_phones, "Phones"),
                CategoryRadio(R.drawable.ic_computers, "Computer"),
                CategoryRadio(R.drawable.ic_health, "Health"),
                CategoryRadio(R.drawable.ic_books, "Books")
            )
        )
    }

    private var checkedItem = 0

    fun setCheckedItem(position: Int) {
        if (checkedItem == position) return
        val previousCheckedItem = checkedItem
        checkedItem = position
        notifyItemChanged(previousCheckedItem)
        notifyItemChanged(checkedItem)
    }

    override fun compareItems(old: CategoryRadio, new: CategoryRadio): Boolean = old == new

    override fun createBinding(
        inflater: LayoutInflater, parent: ViewGroup
    ): ItemCategoryBinding {
        return ItemCategoryBinding.inflate(inflater, parent, false)
    }

    override fun bind(
        binding: ItemCategoryBinding, itemView: View, item: CategoryRadio, position: Int
    ) = with(binding) {
        val drawable = ContextCompat.getDrawable(itemView.context, item.imageId)
        if (position == checkedItem) {
            drawable?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                ContextCompat.getColor(itemView.context, R.color.white),
                BlendModeCompat.SRC_ATOP
            )
            image.setBackgroundResource(R.drawable.orange_oval)
            title.setTextColor(ContextCompat.getColor(itemView.context, R.color.orange))
        } else {
            drawable?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                ContextCompat.getColor(itemView.context, R.color.gray),
                BlendModeCompat.SRC_ATOP
            )
            image.setBackgroundResource(R.drawable.white_oval)
            title.setTextColor(ContextCompat.getColor(itemView.context, R.color.blue))
        }
        image.setImageDrawable(drawable)

        title.text = item.title
        itemView.setOnClickListener {
            if (checkedItem == position) return@setOnClickListener
            listener.setCheckedItem(position)
            val previousCheckedItem = checkedItem
            checkedItem = position
            notifyItemChanged(previousCheckedItem)
            notifyItemChanged(checkedItem)
        }
    }
}