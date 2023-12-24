package com.sarathexp.offlinewallet.domain.model.card

import androidx.compose.runtime.Immutable

@Immutable
data class CardListItem(
    val id: Long = 0,
    val cardNumber: String,
    val nameOnCard: String,
    val alias: String?,
    val cardNetwork: CardNetwork,
    val cardType: CardType,
    val providerName: String,
    val providerLogoRes: Int,
)
