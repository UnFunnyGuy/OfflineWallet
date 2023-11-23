package com.sarathexp.offlinewallet.data.local.dao

import androidx.room.Dao
import com.sarathexp.offlinewallet.data.model.entity.BankAccountEntity
import com.sarathexp.offlinewallet.util.BaseDao

@Dao
interface BankAccountDao : BaseDao<BankAccountEntity>