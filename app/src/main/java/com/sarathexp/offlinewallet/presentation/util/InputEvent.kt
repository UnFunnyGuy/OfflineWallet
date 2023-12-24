package com.sarathexp.offlinewallet.presentation.util

data class InputEvent(
    val value: String,
    val onValueChange: (String) -> Unit,
)
data class TypeInputEvent<T>(
    val value: T,
    val onValueChange: (T) -> Unit,
)