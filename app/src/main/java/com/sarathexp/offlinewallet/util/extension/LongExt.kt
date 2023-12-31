package com.sarathexp.offlinewallet.util.extension

val Long?.formattedString: String
    get() = if (this == 0L || this == null) "" else this.toString()
