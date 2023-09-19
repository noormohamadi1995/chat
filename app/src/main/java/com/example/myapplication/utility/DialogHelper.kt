package com.example.myapplication.utility

import android.app.ActionBar
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.databinding.CustomLoadingBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogHelper {
    fun createLoadingDialog(context: Context): AlertDialog = MaterialAlertDialogBuilder(context)
        .setView(
            CustomLoadingBinding.inflate(
                LayoutInflater.from(context)
            ).apply {
                this.root.layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.MATCH_PARENT
                )
            }.root
        )
        .setCancelable(false)
        .show()
}