package com.sarathexp.offlinewallet.domain.use_case.bank_account

import com.sarathexp.offlinewallet.app.base.BaseUseCase
import com.sarathexp.offlinewallet.domain.model.account.BankAccount
import com.sarathexp.offlinewallet.domain.repository.BankAccountRepository

class DeleteBankAccount(
    private val repository: BankAccountRepository
) : BaseUseCase<BankAccount, Boolean> {

    override suspend fun perform(params: BankAccount): Boolean {
        return repository.deleteBankAccount(params)
    }

}