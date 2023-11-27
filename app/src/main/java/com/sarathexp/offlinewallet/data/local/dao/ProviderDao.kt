package com.sarathexp.offlinewallet.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.sarathexp.offlinewallet.data.model.entity.ProviderEntity
import com.sarathexp.offlinewallet.app.base.BaseDao

@Dao
interface ProviderDao: BaseDao<ProviderEntity> {
    @Query("DELETE FROM providers")
    suspend fun clearAll()
}