package com.example.test10_final.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.test10_final.R

fun ImageView.loadImage (url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)
        .into(this)
}