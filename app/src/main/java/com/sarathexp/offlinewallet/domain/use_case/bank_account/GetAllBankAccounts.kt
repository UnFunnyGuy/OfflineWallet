package com.sarathexp.offlinewallet.domain.use_case.bank_account

import com.sarathexp.offlinewallet.domain.model.account.BankAccount
import com.sarathexp.offlinewallet.domain.repository.BankAccountRepository
import com.sarathexp.offlinewallet.app.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class GetAllBankAccounts(private val repository: BankAccountRepository) :
    BaseUseCase<Nothing, Flow<List<BankAccount>>>() {

    override suspend fun run(params: Nothing): Flow<List<BankAccount>> {
        return repository.getAllBankAccounts()
    }
}
