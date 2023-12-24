package com.sarathexp.offlinewallet.domain.use_case.auth_history

import com.sarathexp.offlinewallet.app.base.BaseUseCase
import com.sarathexp.offlinewallet.domain.repository.AuthHistoryRepository

class DeleteAuthHistoryById(
    private val repository: AuthHistoryRepository
) : BaseUseCase<Long, Boolean> {

    override suspend fun perform(params: Long): Boolean {
        return repository.deleteAuthHistoryById(params)
    }

}
