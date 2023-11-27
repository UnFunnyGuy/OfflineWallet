package com.sarathexp.offlinewallet.domain.use_case.bank_account

import com.sarathexp.offlinewallet.domain.model.account.BankAccount
import com.sarathexp.offlinewallet.domain.repository.BankAccountRepository
import com.sarathexp.offlinewallet.app.base.BaseUseCase

class GetBankAccountById(private val repository: BankAccountRepository) :
    BaseUseCase<Long, BankAccount?>() {

    override suspend fun run(params: Long): BankAccount? {
        return repository.getBankAccountById(params)
    }
}
