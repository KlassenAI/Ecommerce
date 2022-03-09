package com.android.feature_main.presentation.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.android.core.extensions.setImage
import com.android.core.ui.GenericAdapter
import com.android.feature_main.databinding.ItemHotSalesBinding
import com.android.feature_main.domain.model.HomeStore

class HotSalesAdapter(
    private val listener: Listener
): GenericAdapter<HomeStore, ItemHotSalesBinding>() {

    interface Listener {
        fun onItemClick(item: HomeStore)
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemHotSalesBinding {
        return ItemHotSalesBinding.inflate(inflater, parent, false)
    }

    override fun bind(
        binding: ItemHotSalesBinding, itemView: View, item: HomeStore, position: Int
    ) = with(binding) {
        imageIsNew.isVisible = item.isNew
        textIsNew.isVisible = item.isNew
        title.setUnderlineText(item.title)
        subtitle.setUnderlineText(item.subtitle)
        picture.setImage(item.picture)
        itemView.setOnClickListener { listener.onItemClick(item) }
    }

    private fun TextView.setUnderlineText(newText: String) {
        text = newText
        paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }
}