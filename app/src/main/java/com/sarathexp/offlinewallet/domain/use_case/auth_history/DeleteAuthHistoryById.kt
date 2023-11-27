package com.sarathexp.offlinewallet.domain.use_case.auth_history

import com.sarathexp.offlinewallet.domain.repository.AuthHistoryRepository
import com.sarathexp.offlinewallet.app.base.BaseUseCase

class DeleteAuthHistoryById(
    private val repository: AuthHistoryRepository
) : BaseUseCase<Long, Boolean>() {

    override suspend fun run(params: Long): Boolean {
        return repository.deleteAuthHistoryById(params)
    }
}
