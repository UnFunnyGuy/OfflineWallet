package com.sarathexp.offlinewallet.domain.use_case.bank_account

import com.sarathexp.offlinewallet.domain.repository.BankAccountRepository
import com.sarathexp.offlinewallet.util.BaseUseCase

class DeleteBankAccountById(private val repository: BankAccountRepository): BaseUseCase<Long,Boolean>() {
    override suspend fun run(params: Long): Boolean {
        return repository.deleteBankAccountById(params)
    }
}
