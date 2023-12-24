package com.sarathexp.offlinewallet.domain.use_case.card

import com.sarathexp.offlinewallet.app.base.BaseUseCase
import com.sarathexp.offlinewallet.domain.model.card.CardListItem
import com.sarathexp.offlinewallet.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow

class GetAllCards(
    private val repository: CardRepository
) : BaseUseCase<String, List<CardListItem>> {

    override fun performStreaming(params: String?): Flow<List<CardListItem>> {
        return repository.getAllCards(params)
    }
}
