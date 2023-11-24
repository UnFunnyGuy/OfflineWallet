package com.sarathexp.offlinewallet.domain.use_case.bank_account

import com.sarathexp.offlinewallet.domain.model.account.BankAccount
import com.sarathexp.offlinewallet.domain.repository.BankAccountRepository
import com.sarathexp.offlinewallet.util.BaseUseCase

class AddBankAccountUseCase(
    private val repository: BankAccountRepository
) : BaseUseCase<BankAccount,Boolean>() {

    override suspend fun run(params: BankAccount): Boolean {
        return repository.addBankAccount(params)
    }

}