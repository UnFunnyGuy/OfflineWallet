package com.sarathexp.offlinewallet.domain.use_case.bank_account

import com.sarathexp.offlinewallet.app.base.BaseUseCase
import com.sarathexp.offlinewallet.domain.model.account.BankAccount
import com.sarathexp.offlinewallet.domain.repository.BankAccountRepository

class GetBankAccountById(private val repository: BankAccountRepository) :
    BaseUseCase<Long, BankAccount?> {

    override suspend fun perform(params: Long): BankAccount? {
        return repository.getBankAccountById(params)
    }
}
