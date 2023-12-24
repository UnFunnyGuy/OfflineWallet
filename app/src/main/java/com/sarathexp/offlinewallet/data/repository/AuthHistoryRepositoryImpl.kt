package com.sarathexp.offlinewallet.data.repository

import com.sarathexp.offlinewallet.data.local.dao.AuthHistoryDao
import com.sarathexp.offlinewallet.domain.model.auth.AuthHistory
import com.sarathexp.offlinewallet.domain.repository.AuthHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthHistoryRepositoryImpl @Inject constructor(
    private val authHistoryDao: AuthHistoryDao
) : AuthHistoryRepository {
    override suspend fun addAuthHistory(authHistory: AuthHistory): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAuthHistoryById(authHistoryId: Long): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getAuthHistoryById(authHistoryId: Long): AuthHistory? {
        TODO("Not yet implemented")
    }

    override fun getAllAuthHistories(): Flow<List<AuthHistory>> {
        TODO("Not yet implemented")
    }

    override suspend fun lastAuthenticated(): AuthHistory? {
        TODO("Not yet implemented")
    }

}