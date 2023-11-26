package com.sarathexp.offlinewallet.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sarathexp.offlinewallet.data.local.converter.LocalDateTimeConverter
import com.sarathexp.offlinewallet.data.local.dao.AuthHistoryDao
import com.sarathexp.offlinewallet.data.local.dao.BankAccountDao
import com.sarathexp.offlinewallet.data.local.dao.CardDao
import com.sarathexp.offlinewallet.data.local.dao.ProviderDao
import com.sarathexp.offlinewallet.data.model.entity.AuthHistoryEntity
import com.sarathexp.offlinewallet.data.model.entity.BankAccountEntity
import com.sarathexp.offlinewallet.data.model.entity.CardEntity
import com.sarathexp.offlinewallet.data.model.entity.ProviderEntity
import com.sarathexp.offlinewallet.util.ProviderHandler
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(
    entities =
        [
            BankAccountEntity::class,
            ProviderEntity::class,
            CardEntity::class,
            AuthHistoryEntity::class
        ],
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

    companion object {

        // TODO: Clean secure string
        private val test: ByteArray = SQLiteDatabase.getBytes("test_passphrase".toCharArray())
        private val supportFactory = SupportFactory(test)

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context, providerHandler: ProviderHandler): AppDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context, providerHandler).also { INSTANCE = it }
                }
        }

        private fun buildDatabase(
            context: Context,
            providerHandler: ProviderHandler,
            openHelperFactory: SupportFactory? = null
        ): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "OfflineWallet.db")
                .openHelperFactory(openHelperFactory)
                .addCallback(
                    object : Callback() {
                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            INSTANCE?.let { appDatabase ->
                                providerHandler.checkProviders(appDatabase.providerDao())
                            }
                        }
                    }
                )
                .build()
        }
    }
}

/*class TransactionProvider(private val db: AppDatabase) {
    suspend fun <R> runAsTransaction(block: suspend () -> R): R {
        return db.withTransaction(block)
    }
}*/
