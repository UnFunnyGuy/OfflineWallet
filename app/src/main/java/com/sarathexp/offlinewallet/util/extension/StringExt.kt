package com.sarathexp.offlinewallet.util.extension

private val numPattern = Regex("^\\d*\\.?\\d*\$")

fun String.numFieldCheck(): Boolean {
    return numPattern.matches(this)
}