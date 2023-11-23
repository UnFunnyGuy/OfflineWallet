package com.sarathexp.offlinewallet.data.repository

import com.sarathexp.offlinewallet.data.local.dao.BankAccountDao
import com.sarathexp.offlinewallet.domain.model.account.BankAccount
import com.sarathexp.offlinewallet.domain.repository.BankAccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BankAccountRepositoryImpl @Inject constructor(
    private val bankAccountDao: BankAccountDao
) : BankAccountRepository {

    override suspend fun addBankAccount(bankAccount: BankAccount): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateBankAccount(bankAccount: BankAccount): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBankAccount(bankAccount: BankAccount): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBankAccountById(bankAccountId: Long): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getBankAccountById(bankAccountId: Long): BankAccount? {
        TODO("Not yet implemented")
    }

    override suspend fun getAllBankAccounts(): Flow<List<BankAccount>> {
        TODO("Not yet implemented")
    }
}
