package com.sarathexp.offlinewallet.domain.use_case.card

import com.sarathexp.offlinewallet.app.base.BaseUseCase
import com.sarathexp.offlinewallet.domain.model.card.Card
import com.sarathexp.offlinewallet.domain.repository.CardRepository

class GetCardById(
    private val repository: CardRepository
) : BaseUseCase<Long, Card?> {

    override suspend fun perform(params: Long): Card? {
        return repository.getCardById(params)
    }
}
