package com.sarathexp.offlinewallet.domain.use_case.auth_history

import com.sarathexp.offlinewallet.app.base.BaseUseCase
import com.sarathexp.offlinewallet.domain.model.auth.AuthHistory
import com.sarathexp.offlinewallet.domain.repository.AuthHistoryRepository
import kotlinx.coroutines.flow.Flow

class GetAllAuthHistories(private val repository: AuthHistoryRepository) :
    BaseUseCase<Unit, List<AuthHistory>> {

    override fun performStreaming(params: Unit?): Flow<List<AuthHistory>> {
        return repository.getAllAuthHistories()
    }
}
