package com.sarathexp.offlinewallet.domain.use_case.card

import com.sarathexp.offlinewallet.domain.model.card.Card
import com.sarathexp.offlinewallet.domain.repository.CardRepository
import com.sarathexp.offlinewallet.util.BaseUseCase

class UpdateCard(private val repository: CardRepository) : BaseUseCase<Card, Boolean>() {

    override suspend fun run(params: Card): Boolean {
        return repository.updateCard(params)
    }
}
