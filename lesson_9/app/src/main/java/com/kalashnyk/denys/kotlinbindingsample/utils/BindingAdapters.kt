package com.kalashnyk.denys.kotlinbindingsample.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso


class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("app:url")
        fun loadImage(view: ImageView, url: String?) {
            Picasso.with(view.context)
                .load(url)
                .into(view)
        }
    }
}