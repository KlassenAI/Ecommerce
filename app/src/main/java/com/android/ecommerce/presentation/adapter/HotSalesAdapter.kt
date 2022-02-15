package com.android.ecommerce.presentation.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.android.ecommerce.databinding.ItemHotSalesBinding
import com.android.ecommerce.domain.model.HomeStore
import com.android.ecommerce.extensions.setImage

class HotSalesAdapter: GenericAdapter<HomeStore, ItemHotSalesBinding>() {

    override fun compareItems(old: HomeStore, new: HomeStore): Boolean = old.id == new.id

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemHotSalesBinding {
        return ItemHotSalesBinding.inflate(inflater, parent, false)
    }

    override fun bind(
        binding: ItemHotSalesBinding, itemView: View, item: HomeStore, position: Int
    ) = with(binding) {
        imageIsNew.isVisible = item.isNew
        textIsNew.isVisible = item.isNew
        title.text = item.title
        title.paintFlags = title.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        subtitle.text = item.subtitle
        subtitle.paintFlags = subtitle.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        picture.setImage(item.picture)
    }
}