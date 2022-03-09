package com.android.feature_cart.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.core.extensions.setImage
import com.android.core.ui.GenericAdapter
import com.android.feature_cart.R
import com.android.feature_cart.databinding.BasketItemBinding
import com.android.feature_cart.domain.model.BasketItem

class BasketItemAdapter(
    private val listener: Listener
): GenericAdapter<BasketItem, BasketItemBinding>() {

    interface Listener {
        fun onCheckoutChanged(checkout: Int)
    }

    private var numbers: ArrayList<Int> = arrayListOf()

    override fun submitList(value: List<BasketItem>) {
        super.submitList(value)
        numbers = ArrayList(Array(value.size) { 1 }.toList())
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): BasketItemBinding {
        return BasketItemBinding.inflate(inflater, parent, false)
    }

    override fun bind(
        binding: BasketItemBinding, itemView: View, item: BasketItem, position: Int
    ) = with(binding) {
        number.text = numbers[position].toString()
        title.text = item.title
        price.text = itemView.context.getString(R.string.price_with_dollar, item.price * numbers[position])
        image.setImage(item.image)
        btnPlus.setOnClickListener {
            if (numbers[position] < 9) {
                numbers[position] += 1
                notifyItemChanged(position)
                listener.onCheckoutChanged(getCheckout())
            }
        }
        btnMinus.setOnClickListener {
            if (numbers[position] > 1) {
                numbers[position] -= 1
                notifyItemChanged(position)
                listener.onCheckoutChanged(getCheckout())
            }
        }
        btnDelete.setOnClickListener {
            Toast.makeText(
                itemView.context,
                "Delete $item",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun getCheckout(): Int {
        var checkout = 0
        numbers.indices.forEach { checkout += getItem(it).price * numbers[it] }
        return checkout
    }
}