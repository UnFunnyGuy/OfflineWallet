package com.sarathexp.offlinewallet.data.model.mapper

import com.sarathexp.offlinewallet.data.model.entity.CardEntity
import com.sarathexp.offlinewallet.domain.model.card.Card

fun CardEntity.toDomain(): Card {
    return Card(
        id = id,
        providerId = providerId,
        cardNumber = cardNumber,
        isLinkedToBank = isLinkedToBank,
        bankAccountId = bankAccountId,
        expiryMonth = expiryMonth,
        expiryYear = expiryYear,
        cvv = cvv,
        nameOnCard = nameOnCard,
        pin = pin,
        alias = alias,
        cardNetwork = cardNetwork,
        cardType = cardType,
        createdAt = createdAt,
        updatedAt = updatedAt

    )
}

fun Card.toEntity() : CardEntity {
    return CardEntity(
        id = id,
        providerId = providerId,
        cardNumber = cardNumber,
        isLinkedToBank = isLinkedToBank,
        bankAccountId = bankAccountId,
        expiryMonth = expiryMonth,
        expiryYear = expiryYear,
        cvv = cvv,
        nameOnCard = nameOnCard.uppercase(),
        pin = pin,
        alias = alias,
        cardNetwork = cardNetwork,
        cardType = cardType,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}