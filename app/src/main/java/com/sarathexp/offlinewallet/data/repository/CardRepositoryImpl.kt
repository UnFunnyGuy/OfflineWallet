package com.sarathexp.offlinewallet.data.repository

import com.sarathexp.offlinewallet.domain.model.card.Card
import com.sarathexp.offlinewallet.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(

) : CardRepository {
    override suspend fun addCard(card: Card): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateCard(card: Card): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCard(card: Card): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCardById(cardId: Long): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getCardById(cardId: Long): Card? {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCards(): Flow<List<Card>> {
        TODO("Not yet implemented")
    }

}