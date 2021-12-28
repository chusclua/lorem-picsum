package com.chus.clua.lorempicsum.presentation.binding

import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.chus.clua.lorempicsum.R
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView


@BindingAdapter("image_url")
fun setImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .placeholder(ColorDrawable(ContextCompat.getColor(view.context, R.color.primaryColor)))
            .error(R.drawable.ic_launcher_foreground)
            .into(view)
    }
}

@BindingAdapter("image_source")
fun imageSource(view: SubsamplingScaleImageView, url: String?) {
    Glide.with(view.context)
        .asBitmap()
        .load(url)
        .centerInside()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                @Nullable resource: Bitmap,
                @Nullable transition: Transition<in Bitmap>?
            ) {
                view.setImage(ImageSource.bitmap(resource))
            }

            override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
        })
}

@BindingAdapter("toast_message")
fun showToast(view: View, message: String?) {
    message?.let { Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show() }
}