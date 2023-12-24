package com.sarathexp.offlinewallet.domain.repository

import com.sarathexp.offlinewallet.domain.model.account.BankAccount
import kotlinx.coroutines.flow.Flow

interface BankAccountRepository {
    suspend fun addBankAccount(bankAccount: BankAccount): Boolean
    suspend fun updateBankAccount(bankAccount: BankAccount): Boolean
    suspend fun deleteBankAccount(bankAccount: BankAccount): Boolean
    suspend fun deleteBankAccountById(bankAccountId: Long): Boolean
    suspend fun getBankAccountById(bankAccountId: Long): BankAccount?
    fun getAllBankAccounts(): Flow<List<BankAccount>>
}