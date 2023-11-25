package com.sarathexp.offlinewallet.domain.use_case.bank_account

data class BankAccountUseCases(
    val addBankAccount: AddBankAccount,
    val updateBankAccountUseCase: UpdateBankAccount,
    val deleteBankAccountUseCase: DeleteBankAccount,
    val deleteBankAccountById: DeleteBankAccountById,
    val getBankAccountById: GetBankAccountById,
    val getAllBankAccounts: GetAllBankAccounts
)
