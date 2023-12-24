package com.sarathexp.offlinewallet.domain.use_case.card

import com.sarathexp.offlinewallet.app.base.BaseUseCase
import com.sarathexp.offlinewallet.domain.model.card.Card
import com.sarathexp.offlinewallet.domain.repository.CardRepository

class AddCard(
    private val repository: CardRepository
) : BaseUseCase<Card, Boolean> {

    override suspend fun perform(params: Card): Boolean {
        return repository.addCard(params)
    }
}
