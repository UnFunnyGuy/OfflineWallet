package com.sarathexp.offlinewallet.domain.model.account

import java.time.LocalDateTime

data class BankAccount(
    val id: Long = 0,
    val bankName: String,
    val providerId: Long,
    val accountHolderName: String,
    val accountNumber: String,
    val ifsc: String,
    val phoneNumber: String?,
    val alias: String?,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
