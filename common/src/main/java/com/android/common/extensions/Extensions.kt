package com.android.common.extensions

import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

fun <VH : RecyclerView.ViewHolder?> RecyclerView.init(
    paramAdapter: RecyclerView.Adapter<VH>
) {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    adapter = paramAdapter
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

fun ImageView.setImage(drawId: Int) {
    Glide.with(this)
        .load(drawId)
        .into(this)
}