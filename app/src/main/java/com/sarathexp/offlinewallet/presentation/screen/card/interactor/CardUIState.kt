package com.sarathexp.offlinewallet.presentation.screen.card.interactor

import com.sarathexp.offlinewallet.app.base.UIState
import com.sarathexp.offlinewallet.domain.model.card.CardListItem
import com.sarathexp.offlinewallet.domain.model.card.CardNetwork
import kotlinx.collections.immutable.ImmutableList

data class CardUIState(
    val searchText: String,
    val cards: ImmutableList<CardListItem>,

    val cardNumber: Long,
    val cardHolder: String,
    val cardExpiry: Long,
    val cardCvv: Long?,
    val cardNetwork: CardNetwork,

): UIState
