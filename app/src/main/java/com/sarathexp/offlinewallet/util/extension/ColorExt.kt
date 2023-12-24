package com.sarathexp.offlinewallet.util.extension


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

fun Color.toHex(): String {
    return String.format("#%08X", this.toArgb())
}

val String.color
    get() = Color(android.graphics.Color.parseColor(this))