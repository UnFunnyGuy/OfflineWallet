package com.sarathexp.offlinewallet.di

import com.sarathexp.offlinewallet.data.repository.AuthHistoryRepositoryImpl
import com.sarathexp.offlinewallet.data.repository.BankAccountRepositoryImpl
import com.sarathexp.offlinewallet.domain.repository.AuthHistoryRepository
import com.sarathexp.offlinewallet.domain.repository.BankAccountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthHistoryRepository(repository: AuthHistoryRepositoryImpl): AuthHistoryRepository

    @Binds
    abstract fun bindBankAccountRepository(repository: BankAccountRepositoryImpl): BankAccountRepository

    @Binds
    abstract fun bindCardRepository(repository: BankAccountRepositoryImpl): BankAccountRepository

}