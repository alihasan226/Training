package com.usl.usl.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout

@Suppress("DEPRECATION")
class MyProgressDialog {

    val dialog:Dialog
    private var progressBar: ProgressBar? = null

    constructor(context: Context?) {
        dialog = Dialog(context!!)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)

        val relativeLayout = RelativeLayout(context)
        val layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        relativeLayout.layoutParams = layoutParams
        progressBar = ProgressBar(context)

        val layoutParams_progress = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        layoutParams_progress.addRule(RelativeLayout.CENTER_IN_PARENT)

        val linearlayoutParams_progress = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        linearlayoutParams_progress.gravity = Gravity.CENTER
        progressBar!!.setLayoutParams(layoutParams_progress)
        relativeLayout.addView(progressBar)
        dialog.window!!.setContentView(relativeLayout, layoutParams)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun show() {
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    fun dismiss() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }


    fun setCancelable(cancelable: Boolean) {
        this.dialog.setCancelable(cancelable)
    }


    fun setCanceledOnTouchOutside(flag: Boolean) {
        dialog.setCanceledOnTouchOutside(flag)
    }

    fun setColor(colour: Int) {
        progressBar!!.indeterminateDrawable
            .setColorFilter(colour, PorterDuff.Mode.MULTIPLY)
    }

    fun isShowing(): Boolean {
        return dialog.isShowing
    }

}