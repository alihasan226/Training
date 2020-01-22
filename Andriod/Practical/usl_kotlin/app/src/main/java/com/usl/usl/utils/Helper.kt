package com.usl.usl.utils

import android.app.AlertDialog
import android.content.Context

class Helper {


    fun alert(
        context: Context?,
        message: String?,
        title: String?
    ) {
        val alertDialog = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ok") { dialog, which ->
                // Your code
            }.show()
    }



}