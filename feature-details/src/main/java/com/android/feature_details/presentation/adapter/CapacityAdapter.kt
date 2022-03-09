package com.android.feature_details.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.core.ui.GenericAdapter
import com.android.feature_details.databinding.CapacityItemBinding

class CapacityAdapter: GenericAdapter<String, CapacityItemBinding>() {

    private var mOnItemClickListener: ((item: String) -> Unit)? = null
    fun setOnItemClickListener(action: (item: String) -> Unit) {
        mOnItemClickListener = action
    }

    var selected: String? = null

    override fun submitList(value: List<String>) {
        super.submitList(value)
        selected = value[0]
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): CapacityItemBinding {
        return CapacityItemBinding.inflate(inflater, parent, false)
    }

    override fun bind(
        binding: CapacityItemBinding, itemView: View, item: String, position: Int
    ) = with(binding) {
        capacity.text = item
        capacity.isSelected = item == selected
        itemView.setOnClickListener {
            mOnItemClickListener?.invoke(item)
            initCapacity(item)
        }
    }

    fun initCapacity(item: String) {
        val oldSelected = indexOf(selected)
        val newSelected = indexOf(item)
        selected = item
        notifyItemChanged(newSelected)
        notifyItemChanged(oldSelected)
    }
}