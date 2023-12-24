package com.sarathexp.offlinewallet.domain.model.provider

data class Provider(
    val id: Long,
    val name: String,
    val isBank: Boolean,
    val issuesCards: Boolean,
    val logoRes: Int,
    val iconRes: Int,
)
