package com.android.feature_main.presentation.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.android.core.extensions.setImage
import com.android.core.ui.GenericAdapter
import com.android.feature_main.R
import com.android.feature_main.databinding.ItemBestSellerBinding
import com.android.feature_main.domain.model.BestSeller

class BestSellerAdapter(
    private val listener: Listener
) : GenericAdapter<BestSeller, ItemBestSellerBinding>() {

    interface Listener {
        fun onItemClick(item: BestSeller)
    }

    private val favorites: MutableList<Int> = mutableListOf()

    override fun submitList(value: List<BestSeller>) {
        value.filter { it.isFavorites }.mapTo(favorites) { it.id }
        super.submitList(value)
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemBestSellerBinding {
        return ItemBestSellerBinding.inflate(inflater, parent, false)
    }

    override fun bind(
        binding: ItemBestSellerBinding, itemView: View, item: BestSeller, position: Int
    ) = with(binding) {
        priceWithoutDiscount.text = itemView.context.getPriceText(item.priceWithoutDiscount)
        discountPrice.text = itemView.context.getPriceText(item.discountPrice)
        discountPrice.paintFlags = discountPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        title.text = item.title
        picture.setImage(item.picture)
        favorite.setImage(favorite, item.id)
        favorite.setOnClickListener {
            if (favorites.contains(item.id)) favorites.remove(item.id) else favorites.add(item.id)
            favorite.setImage(favorite, item.id)
        }
        itemView.setOnClickListener { listener.onItemClick(item) }
    }

    private fun Context.getPriceText(price: Int): String {
        return resources.getString(R.string.price_with_dollar, price)
    }

    private fun ImageView.setImage(imageView: ImageView, id: Int) {
        imageView.setImageDrawable(ContextCompat.getDrawable(
            this.context,
            if (favorites.contains(id)) R.drawable.ic_favorite else R.drawable.ic_unloved
        ))
    }
}