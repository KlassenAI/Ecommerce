package com.android.core.extensions

import android.app.Activity
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.android.core.R
import com.android.core.navigation.NavCommand
import com.android.core.navigation.NavCommands
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

fun Fragment.navigateToCart() = navigate(getString(R.string.nav_path_cart))
fun Fragment.navigateToDetails() = navigate(getString(R.string.nav_path_details))
fun Fragment.navigateToMap() = navigate(getString(R.string.nav_path_map))

private fun Fragment.navigate(path: String) {
    (requireActivity() as? NavigationProvider)?.launch(getNavCommand(path))
}

fun getNavCommand(path: String): NavCommand {
    return NavCommand(
        target = NavCommands.DeepLink(
            url = Uri.parse(path),
            isModal = false,
            isSingleTop = true
        )
    )
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