package com.sarathexp.offlinewallet.domain.use_case.auth_history

import com.sarathexp.offlinewallet.domain.model.auth.AuthHistory
import com.sarathexp.offlinewallet.domain.repository.AuthHistoryRepository
import com.sarathexp.offlinewallet.app.base.BaseUseCase

class AddAuthHistory(
    private val repository: AuthHistoryRepository
): BaseUseCase<AuthHistory, Boolean>() {

    override suspend fun run(params: AuthHistory): Boolean {
        return repository.addAuthHistory(params)
    }
}
