package com.sarathexp.offlinewallet.domain.model.provider

import androidx.annotation.DrawableRes
import java.time.LocalDateTime

data class Provider(
    val id: Long,
    val name: String,
    val isBank: Boolean,
    val issuesCards: Boolean,
    val logoDrawableRes: String?,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
