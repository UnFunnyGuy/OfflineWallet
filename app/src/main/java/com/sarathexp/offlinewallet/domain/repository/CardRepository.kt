package com.sarathexp.offlinewallet.domain.repository

import com.sarathexp.offlinewallet.domain.model.card.Card
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun addCard(card: Card): Boolean
    suspend fun updateCard(card: Card): Boolean
    suspend fun deleteCard(card: Card): Boolean
    suspend fun deleteCardById(cardId: Long): Boolean
    suspend fun getCardById(cardId: Long): Card?
    suspend fun getAllCards(): Flow<List<Card>>
}