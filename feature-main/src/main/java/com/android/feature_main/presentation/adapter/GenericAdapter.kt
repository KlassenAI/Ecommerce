package com.android.feature_main.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class GenericAdapter<T, VB : ViewBinding> :
    RecyclerView.Adapter<GenericAdapter<T, VB>.ViewHolder>() {

    private var items: List<T> = emptyList()

    fun submitList(value: List<T>) {
        val diffResult = DiffUtil.calculateDiff(GenericDiffUtilCallback(items, value))
        items = value
        diffResult.dispatchUpdatesTo(this)
    }

    inner class GenericDiffUtilCallback(
        private val oldList: List<T>,
        private val newList: List<T>
    ): DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(old: Int, new: Int) = compareItems(oldList[old], newList[new])
        override fun areContentsTheSame(old: Int, new: Int) = oldList[old] == newList[new]
    }

    abstract fun compareItems(old: T, new: T): Boolean

    fun getItem(position: Int) = items[position]

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        createBinding(LayoutInflater.from(parent.context), parent)
    )

    abstract fun createBinding(inflater: LayoutInflater, parent: ViewGroup): VB

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position), position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }

    abstract fun bind(binding: VB, itemView: View, item: T, position: Int)

    inner class ViewHolder(private val binding: VB) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: T, position: Int) {
            bind(binding, itemView, item, position)
        }
    }
}