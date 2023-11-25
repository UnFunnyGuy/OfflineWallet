package com.sarathexp.offlinewallet.domain.use_case.card

import com.sarathexp.offlinewallet.domain.repository.CardRepository
import com.sarathexp.offlinewallet.util.BaseUseCase

class DeleteCardById(private val repository: CardRepository) : BaseUseCase<Long, Boolean>() {

    override suspend fun run(params: Long): Boolean {
        return repository.deleteCardById(params)
    }
}
