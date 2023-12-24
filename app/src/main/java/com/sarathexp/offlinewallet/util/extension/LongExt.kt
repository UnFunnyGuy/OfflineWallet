package com.sarathexp.offlinewallet.util.extension

val Long.formattedString: String
    get() = if (this == 0L)"" else this.toString()