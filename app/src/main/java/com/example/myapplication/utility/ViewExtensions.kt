package com.example.myapplication.utility

import android.view.View
import android.widget.EditText
import androidx.core.view.isVisible


fun EditText.isEmptyOrNull() : Boolean{
    return this.text.isNullOrEmpty()
}

fun View.visible() { this.visibility = View.VISIBLE }
fun View.gone() { this.visibility = View.GONE }

fun View.changeVisibility(isVisible: Boolean) =
    if (this.isVisible == isVisible) Unit else if (isVisible) visible() else gone()

fun View.toggleVisibility() = if (this.isVisible) gone() else visible()
