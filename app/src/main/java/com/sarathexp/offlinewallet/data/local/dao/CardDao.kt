package com.sarathexp.offlinewallet.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.sarathexp.offlinewallet.app.base.BaseDao
import com.sarathexp.offlinewallet.data.model.entity.CardEntity
import com.sarathexp.offlinewallet.domain.model.card.CardListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao : BaseDao<CardEntity> {

    @Query(
        """
        SELECT
        card.id,
        card.cardNumber,
        card.nameOnCard,
        card.alias,
        card.cardNetwork,
        card.cardType,
        card.color,
        provider.name AS providerName,
        provider.logoRes AS providerLogoRes
        FROM cards AS card
        INNER JOIN providers AS provider ON card.providerId = provider.id
        WHERE card.cardNumber LIKE '%' || :query || '%'
        OR card.nameOnCard LIKE '%' || :query || '%'
        OR alias LIKE '%' || :query || '%'
        ORDER BY card.createdAt DESC
    """
    )
    fun getAllCards(query: String?): Flow<List<CardListItem>>
}
