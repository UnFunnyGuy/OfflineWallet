package com.sarathexp.offlinewallet.domain.use_case.auth_history

import com.sarathexp.offlinewallet.app.base.BaseUseCase
import com.sarathexp.offlinewallet.domain.model.auth.AuthHistory
import com.sarathexp.offlinewallet.domain.repository.AuthHistoryRepository

class GetAuthHistoryById(
    private val repository: AuthHistoryRepository
) : BaseUseCase<Long, AuthHistory?>{

    override suspend fun perform(params: Long): AuthHistory? {
        return repository.getAuthHistoryById(params)
    }
}
