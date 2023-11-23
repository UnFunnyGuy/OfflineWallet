package com.sarathexp.offlinewallet.domain.repository

import com.sarathexp.offlinewallet.domain.model.auth.AuthHistory
import kotlinx.coroutines.flow.Flow

interface AuthHistoryRepository {
    suspend fun addAuthHistory(authHistory: AuthHistory): Boolean
    suspend fun updateAuthHistory(authHistory: AuthHistory): Boolean
    suspend fun deleteAuthHistory(authHistory: AuthHistory): Boolean
    suspend fun deleteAuthHistoryById(authHistoryId: Long): Boolean
    suspend fun getAuthHistoryById(authHistoryId: Long): AuthHistory?
    suspend fun getAllAuthHistories(): Flow<List<AuthHistory>>
}