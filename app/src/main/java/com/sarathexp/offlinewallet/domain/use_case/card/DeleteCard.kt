package com.sarathexp.offlinewallet.domain.use_case.card

import com.sarathexp.offlinewallet.domain.model.card.Card
import com.sarathexp.offlinewallet.domain.repository.CardRepository
import com.sarathexp.offlinewallet.app.base.BaseUseCase

class DeleteCard(private val repository: CardRepository) : BaseUseCase<Card, Boolean>() {

    override suspend fun run(params: Card): Boolean {
        return repository.deleteCard(params)
    }
}
