package com.android.feature_details.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.core.ui.GenericAdapter
import com.android.feature_details.databinding.StarItemBinding

class StarAdapter: GenericAdapter<String, StarItemBinding>() {

    fun submitList(size: Int) {
        super.submitList(Array(size) { "" }.toList())
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): StarItemBinding {
        return StarItemBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: StarItemBinding, itemView: View, item: String, position: Int) {}
}