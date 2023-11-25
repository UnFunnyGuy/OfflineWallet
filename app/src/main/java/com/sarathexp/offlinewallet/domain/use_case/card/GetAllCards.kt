package com.sarathexp.offlinewallet.domain.use_case.card

import com.sarathexp.offlinewallet.domain.model.card.Card
import com.sarathexp.offlinewallet.domain.repository.CardRepository
import com.sarathexp.offlinewallet.util.BaseUseCase
import kotlinx.coroutines.flow.Flow

class GetAllCards(private val repository: CardRepository) : BaseUseCase<Nothing, Flow<List<Card>>>() {

    override suspend fun run(params: Nothing): Flow<List<Card>> {
        return repository.getAllCards()
    }
}
