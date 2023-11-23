package com.sarathexp.offlinewallet.domain.model.auth

import java.time.LocalDateTime

data class AuthHistory(
    val id: Long = 0,
    val authItemId: Long,
    val authItem: AuthItem,
    val authenticatedAt: LocalDateTime = LocalDateTime.now()
)
