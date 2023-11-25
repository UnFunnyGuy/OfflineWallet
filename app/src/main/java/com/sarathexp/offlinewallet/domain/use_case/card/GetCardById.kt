package com.sarathexp.offlinewallet.domain.use_case.card

import com.sarathexp.offlinewallet.domain.model.card.Card
import com.sarathexp.offlinewallet.domain.repository.CardRepository
import com.sarathexp.offlinewallet.util.BaseUseCase

class GetCardById(private val repository: CardRepository) : BaseUseCase<Long, Card?>() {

    override suspend fun run(params: Long): Card? {
        return repository.getCardById(params)
    }
}
