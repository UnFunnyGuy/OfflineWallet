package com.sarathexp.offlinewallet.di

import android.content.Context
import androidx.room.Room
import com.sarathexp.offlinewallet.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // TODO: Clean secure string
    private val test: ByteArray = SQLiteDatabase.getBytes("test_passphrase".toCharArray())
    private val supportFactory = SupportFactory(test)

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "OfflineWallet")
//            .createFromAsset("database/default.db")
//            .openHelperFactory(supportFactory)
            .openHelperFactory(null)
            .build()

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
