package com.sarathexp.offlinewallet.domain.use_case.bank_account

import com.sarathexp.offlinewallet.domain.model.account.BankAccount
import com.sarathexp.offlinewallet.domain.repository.BankAccountRepository
import com.sarathexp.offlinewallet.util.BaseUseCase

class UpdateBankAccount(private val bankAccountRepository: BankAccountRepository) :
    BaseUseCase<BankAccount, Boolean>() {

    override suspend fun run(params: BankAccount): Boolean {
        return bankAccountRepository.updateBankAccount(params)
    }

}
