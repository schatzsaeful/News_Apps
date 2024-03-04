package com.contoh.newsapps.utils.ext

import android.util.Log
import android.widget.ImageView
import com.contoh.newsapps.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso

fun ImageView.loadImageUrl(
    imageUrl: String,
    placeholder: Int = R.color.white,
    error: Int = R.color.white,
    rotate: Float = 0f
) {
    if (imageUrl.isEmpty()) return

    Picasso.get()
        .load(imageUrl)
        .fit()
        .centerCrop()
        .rotate(rotate)
        .placeholder(placeholder)
        .error(error)
        .into(this)
}

inline fun <reified T> String.safeGenerateModel(): T? {
    return try {
        Gson().fromJson(this, T::class.java)
    } catch (e: Exception) {
        null
    }
}

inline fun <reified T> callOrNull(block: () -> T) = try {
    block.invoke()
} catch (e: Exception) {
    Log.d("DEBUG_MAIN", "error: ${e.message}")
    null
}
