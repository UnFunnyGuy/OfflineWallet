package com.sarathexp.offlinewallet.di

import com.sarathexp.offlinewallet.domain.repository.BankAccountRepository
import com.sarathexp.offlinewallet.domain.use_case.bank_account.AddBankAccountUseCase
import com.sarathexp.offlinewallet.domain.use_case.bank_account.BankAccountUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideBankAccountUseCases(repository: BankAccountRepository) =
        BankAccountUseCases(addBankAccountUseCase = AddBankAccountUseCase(repository))

}
