package com.android.ecommerce.presentation.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.android.ecommerce.R
import com.android.ecommerce.databinding.ItemBestSellerBinding
import com.android.ecommerce.domain.model.BestSeller
import com.android.ecommerce.extensions.setImage

class BestSellerAdapter: GenericAdapter<BestSeller, ItemBestSellerBinding>() {

    override fun compareItems(old: BestSeller, new: BestSeller): Boolean = old.id == new.id

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemBestSellerBinding {
        return ItemBestSellerBinding.inflate(inflater, parent, false)
    }

    override fun bind(
        binding: ItemBestSellerBinding, itemView: View, item: BestSeller, position: Int
    ) = with(binding) {
        priceWithoutDiscount.text = itemView.resources.getString(R.string.price_with_dollar, item.priceWithoutDiscount)
        discountPrice.text = itemView.resources.getString(R.string.price_with_dollar, item.discountPrice)
        discountPrice.paintFlags = discountPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        title.text = item.title
        picture.setImage(item.picture)
        if (item.isFavorites) {
            favorite.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_favorite))
        } else {
            favorite.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_unloved))
        }
    }
}