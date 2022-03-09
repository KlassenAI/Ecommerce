package com.android.core.extensions

import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.android.core.navigation.NavCommand
import com.android.core.navigation.NavigationProvider
import com.bumptech.glide.Glide

fun Fragment.showToast(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
fun Fragment.log(text: String) = Log.d("TEST: ${this.javaClass.name}", text)

fun Fragment.navigateUp() = findNavController().navigateUp()

fun <VH : RecyclerView.ViewHolder?> RecyclerView.init(
    paramAdapter: RecyclerView.Adapter<VH>,
    horizontal: Boolean = true
) {
    layoutManager = LinearLayoutManager(
        context,
        if (horizontal) LinearLayoutManager.HORIZONTAL else LinearLayoutManager.VERTICAL,
        false
    )
    adapter = paramAdapter
}

fun Fragment.navigate(navCommand: NavCommand) {
    (requireActivity() as? NavigationProvider)?.launch(navCommand)
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