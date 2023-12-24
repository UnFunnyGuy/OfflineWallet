package com.sarathexp.offlinewallet.domain.use_case.card

import com.sarathexp.offlinewallet.app.base.BaseUseCase
import com.sarathexp.offlinewallet.domain.repository.CardRepository

class DeleteCardById(
    private val repository: CardRepository
) : BaseUseCase<Long, Boolean> {

    override suspend fun perform(params: Long): Boolean {
        return repository.deleteCardById(params)
    }
}
