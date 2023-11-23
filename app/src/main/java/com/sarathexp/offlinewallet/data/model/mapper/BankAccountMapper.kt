package com.sarathexp.offlinewallet.data.model.mapper

import com.sarathexp.offlinewallet.data.model.entity.BankAccountEntity
import com.sarathexp.offlinewallet.domain.model.account.BankAccount

fun BankAccountEntity.toDomain(): BankAccount {
    return BankAccount(
        id = id,
        bankName = bankName,
        providerId = providerId,
        accountHolderName = accountHolderName,
        accountNumber = accountNumber,
        ifsc = ifsc,
        phoneNumber = phoneNumber,
        alias = alias,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun BankAccount.toEntity(): BankAccountEntity {
    return BankAccountEntity(
        id = id,
        bankName = bankName,
        providerId = providerId,
        accountHolderName = accountHolderName,
        accountNumber = accountNumber,
        ifsc = ifsc,
        phoneNumber = phoneNumber,
        alias = alias,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}