package com.io.enjoydrivintask.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Service
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.io.enjoydrivintask.R


fun Activity.showMessage(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
class SendSingleItemListener<T>(val item: (item: T) -> Unit) {
    fun sendItem(item: T) = item(item)
}

object ViewBinindngAdapter {
    @SuppressLint("CheckResult")
    @JvmStatic
    @BindingAdapter("bind_image", requireAll = false)
    fun bindImage(
        image: AppCompatImageView,
        imagePath: String? = null,
    ) {
        Glide.with(image).load(imagePath).apply {
            placeholder(R.drawable.background)
            error(R.drawable.background)
        }.into(image)
    }
}

fun View.hideKeyboard() {
    (this.context?.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(this.windowToken, 0)
}
