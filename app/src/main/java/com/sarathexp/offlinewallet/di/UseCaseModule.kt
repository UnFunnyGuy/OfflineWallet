package com.sarathexp.offlinewallet.di

import com.sarathexp.offlinewallet.domain.repository.AuthHistoryRepository
import com.sarathexp.offlinewallet.domain.repository.BankAccountRepository
import com.sarathexp.offlinewallet.domain.repository.CardRepository
import com.sarathexp.offlinewallet.domain.use_case.auth_history.AddAuthHistory
import com.sarathexp.offlinewallet.domain.use_case.auth_history.AuthHistoryUseCases
import com.sarathexp.offlinewallet.domain.use_case.auth_history.DeleteAuthHistoryById
import com.sarathexp.offlinewallet.domain.use_case.auth_history.GetAllAuthHistories
import com.sarathexp.offlinewallet.domain.use_case.auth_history.GetAuthHistoryById
import com.sarathexp.offlinewallet.domain.use_case.bank_account.AddBankAccount
import com.sarathexp.offlinewallet.domain.use_case.bank_account.BankAccountUseCases
import com.sarathexp.offlinewallet.domain.use_case.bank_account.DeleteBankAccount
import com.sarathexp.offlinewallet.domain.use_case.bank_account.DeleteBankAccountById
import com.sarathexp.offlinewallet.domain.use_case.bank_account.GetAllBankAccounts
import com.sarathexp.offlinewallet.domain.use_case.bank_account.GetBankAccountById
import com.sarathexp.offlinewallet.domain.use_case.bank_account.UpdateBankAccount
import com.sarathexp.offlinewallet.domain.use_case.card.AddCard
import com.sarathexp.offlinewallet.domain.use_case.card.CardUseCases
import com.sarathexp.offlinewallet.domain.use_case.card.DeleteCard
import com.sarathexp.offlinewallet.domain.use_case.card.DeleteCardById
import com.sarathexp.offlinewallet.domain.use_case.card.GetAllCards
import com.sarathexp.offlinewallet.domain.use_case.card.GetCardById
import com.sarathexp.offlinewallet.domain.use_case.card.UpdateCard
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
        BankAccountUseCases(
            addBankAccount = AddBankAccount(repository),
            updateBankAccountUseCase = UpdateBankAccount(repository),
            deleteBankAccountUseCase = DeleteBankAccount(repository),
            deleteBankAccountById = DeleteBankAccountById(repository),
            getBankAccountById = GetBankAccountById(repository),
            getAllBankAccounts = GetAllBankAccounts(repository)
        )

    @Provides
    @Singleton
    fun provideCardUseCases(repository: CardRepository) =
        CardUseCases(
            addCard = AddCard(repository),
            updateCard = UpdateCard(repository),
            deleteCard = DeleteCard(repository),
            deleteCardById = DeleteCardById(repository),
            getCardById = GetCardById(repository),
            getAllCards = GetAllCards(repository)
        )

    @Provides
    @Singleton
    fun provideAddBankAccount(repository: AuthHistoryRepository) =
        AuthHistoryUseCases(
            addAuthHistory = AddAuthHistory(repository),
            deleteAuthHistoryById = DeleteAuthHistoryById(repository),
            getAuthHistoryById = GetAuthHistoryById(repository),
            getAllAuthHistories = GetAllAuthHistories(repository)
        )


}
