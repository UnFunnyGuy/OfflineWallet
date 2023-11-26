package com.sarathexp.offlinewallet.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.sarathexp.offlinewallet.data.model.entity.BankAccountEntity
import com.sarathexp.offlinewallet.util.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface BankAccountDao : BaseDao<BankAccountEntity>{

    @Query("SELECT * FROM bank_accounts")
    fun getAllBankAccounts(): Flow<List<BankAccountEntity>>

}