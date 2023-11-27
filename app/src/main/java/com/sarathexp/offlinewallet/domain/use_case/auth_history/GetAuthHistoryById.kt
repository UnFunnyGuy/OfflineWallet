package com.sarathexp.offlinewallet.domain.use_case.auth_history

import com.sarathexp.offlinewallet.domain.model.auth.AuthHistory
import com.sarathexp.offlinewallet.domain.repository.AuthHistoryRepository
import com.sarathexp.offlinewallet.app.base.BaseUseCase

class GetAuthHistoryById(
    private val repository: AuthHistoryRepository
) : BaseUseCase<Long, AuthHistory?>() {

    override suspend fun run(params: Long): AuthHistory? {
        return repository.getAuthHistoryById(params)
    }
}
