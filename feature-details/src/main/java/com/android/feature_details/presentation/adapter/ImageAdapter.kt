package com.android.feature_details.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.core.extensions.setImage
import com.android.core.ui.GenericAdapter
import com.android.feature_details.databinding.ProductItemBinding

class ImageAdapter: GenericAdapter<String, ProductItemBinding>() {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ProductItemBinding {
        return ProductItemBinding.inflate(inflater, parent, false)
    }

    override fun bind(
        binding: ProductItemBinding, itemView: View, item: String, position: Int
    ) {
        binding.image.setImage(item)
    }
}