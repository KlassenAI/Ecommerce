package com.android.common.extensions

import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

fun <VH : RecyclerView.ViewHolder?> RecyclerView.init(
    paramAdapter: RecyclerView.Adapter<VH>,
    horizontal: Boolean = true,
    reverse: Boolean = false
) {
    layoutManager = LinearLayoutManager(context, if (horizontal) HORIZONTAL else VERTICAL, reverse)
    adapter = paramAdapter
}

fun <VH : RecyclerView.ViewHolder?> RecyclerView.initWithGrid(
    paramAdapter: RecyclerView.Adapter<VH>,
    spanCount: Int = 2
) {
    layoutManager = GridLayoutManager(this.context, spanCount)
    adapter = paramAdapter
}

fun <VH : RecyclerView.ViewHolder?> RecyclerView.initWithSnapping(
    paramAdapter: RecyclerView.Adapter<VH>,
    horizontal: Boolean = true
) {
    init(paramAdapter, horizontal)
    LinearSnapHelper().attachToRecyclerView(this)
}

fun ImageView.setImage(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(CircularProgressDrawable(this.context).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        })
        .centerCrop()
        .into(this)
}
