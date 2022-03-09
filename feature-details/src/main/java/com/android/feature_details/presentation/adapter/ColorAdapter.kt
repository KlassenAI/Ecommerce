package com.android.feature_details.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.android.core.ui.GenericAdapter
import com.android.feature_details.databinding.ColorItemBinding

class ColorAdapter : GenericAdapter<String, ColorItemBinding>() {

    private var mOnItemClickListener: ((item: String) -> Unit)? = null
    fun setOnItemClickListener(action: (item: String) -> Unit) {
        mOnItemClickListener = action
    }

    var selected: String? = null

    override fun submitList(value: List<String>) {
        super.submitList(value)
        selected = value[0]
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ColorItemBinding {
        return ColorItemBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ColorItemBinding, itemView: View, item: String, position: Int) {
        itemView.background.setTint(Color.parseColor(item))
        binding.btnCheck.isVisible = item == selected
        itemView.setOnClickListener {
            mOnItemClickListener?.invoke(item)
            initSelected(item)
        }
    }

    fun initSelected(item: String) {
        val oldSelected = indexOf(selected)
        val newSelected = indexOf(item)
        selected = item
        notifyItemChanged(newSelected)
        notifyItemChanged(oldSelected)
    }
}