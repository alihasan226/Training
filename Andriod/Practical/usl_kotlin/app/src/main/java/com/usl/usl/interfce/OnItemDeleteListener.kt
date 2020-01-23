package com.usl.usl.interfce

import android.view.View

interface OnItemDeleteListener {
    fun OnItemClick(view: View?, position: Int, OPERATION: String?)
}