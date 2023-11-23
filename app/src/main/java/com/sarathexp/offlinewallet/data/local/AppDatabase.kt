package com.sarathexp.offlinewallet.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.withTransaction
import com.sarathexp.offlinewallet.data.local.converter.LocalDateTimeConverter
import com.sarathexp.offlinewallet.data.local.dao.AuthHistoryDao
import com.sarathexp.offlinewallet.data.local.dao.BankAccountDao
import com.sarathexp.offlinewallet.data.local.dao.CardDao
import com.sarathexp.offlinewallet.data.local.dao.ProviderDao
import com.sarathexp.offlinewallet.data.model.entity.BankAccountEntity
import com.sarathexp.offlinewallet.data.model.entity.CardEntity
import com.sarathexp.offlinewallet.data.model.entity.ProviderEntity

@Database(
    entities = [BankAccountEntity::class, ProviderEntity::class, CardEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    value =
        [
            LocalDateTimeConverter::class,
        ]
)
abstract class AppDatabase : RoomDatabase() {

        abstract fun bankAccountDao(): BankAccountDao

        abstract fun providerDao(): ProviderDao

        abstract fun cardDao(): CardDao

        abstract fun authHistoryDao(): AuthHistoryDao
}

/*class TransactionProvider(private val db: AppDatabase) {
    suspend fun <R> runAsTransaction(block: suspend () -> R): R {
        return db.withTransaction(block)
    }
}*/
