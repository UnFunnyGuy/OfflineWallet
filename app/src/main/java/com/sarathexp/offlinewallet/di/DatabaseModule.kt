package com.sarathexp.offlinewallet.di

import android.content.Context
import com.sarathexp.offlinewallet.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase.getInstance(context = context)

    @Provides
    @Singleton
    fun providesBankAccountDao(database: AppDatabase) = database.bankAccountDao()

    @Provides
    @Singleton
    fun providesProviderDao(database: AppDatabase) = database.providerDao()

    @Provides
    @Singleton
    fun providesCardDao(database: AppDatabase) = database.cardDao()

    @Provides
    @Singleton
    fun providesAuthHistoryDao(database: AppDatabase) = database.authHistoryDao()
}
