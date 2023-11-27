package com.sarathexp.offlinewallet.data.local.dao

import androidx.room.Dao
import com.sarathexp.offlinewallet.data.model.entity.AuthHistoryEntity
import com.sarathexp.offlinewallet.app.base.BaseDao

@Dao
interface AuthHistoryDao: BaseDao<AuthHistoryEntity> {
}