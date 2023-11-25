package com.sarathexp.offlinewallet.domain.use_case.auth_history

import com.sarathexp.offlinewallet.domain.model.auth.AuthHistory
import com.sarathexp.offlinewallet.domain.repository.AuthHistoryRepository
import com.sarathexp.offlinewallet.util.BaseUseCase
import kotlinx.coroutines.flow.Flow

class GetAllAuthHistories(private val repository: AuthHistoryRepository) :
    BaseUseCase<Nothing, Flow<List<AuthHistory>>>() {

    override suspend fun run(params: Nothing): Flow<List<AuthHistory>> {
        return repository.getAllAuthHistories()
    }
}
