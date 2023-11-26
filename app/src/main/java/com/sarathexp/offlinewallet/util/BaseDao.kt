package com.sarathexp.offlinewallet.util

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert

interface BaseDao<T> {

    @Upsert
    suspend fun upsert(data: T): Long

    @Update
    suspend fun update(data: T)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(data: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<T>)

    @Delete
    suspend fun delete(data: T)

    @Transaction
    suspend fun withTransaction(block: suspend  () -> Unit){
        block()
    }


}