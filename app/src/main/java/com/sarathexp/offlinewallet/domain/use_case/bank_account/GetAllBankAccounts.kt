package com.sarathexp.offlinewallet.domain.use_case.bank_account

import com.sarathexp.offlinewallet.app.base.BaseUseCase
import com.sarathexp.offlinewallet.domain.model.account.BankAccount
import com.sarathexp.offlinewallet.domain.repository.BankAccountRepository
import kotlinx.coroutines.flow.Flow

class GetAllBankAccounts(private val repository: BankAccountRepository) :
    BaseUseCase<Unit, List<BankAccount>> {

    override fun performStreaming(params: Unit?): Flow<List<BankAccount>> {
        return repository.getAllBankAccounts()
    }
}
