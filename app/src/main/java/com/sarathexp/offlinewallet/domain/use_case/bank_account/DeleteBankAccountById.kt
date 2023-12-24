package com.sarathexp.offlinewallet.domain.use_case.bank_account

import com.sarathexp.offlinewallet.app.base.BaseUseCase
import com.sarathexp.offlinewallet.domain.repository.BankAccountRepository

class DeleteBankAccountById(
    private val repository: BankAccountRepository
): BaseUseCase<Long, Boolean> {
    override suspend fun perform(params: Long): Boolean {
        return repository.deleteBankAccountById(params)
    }
}
