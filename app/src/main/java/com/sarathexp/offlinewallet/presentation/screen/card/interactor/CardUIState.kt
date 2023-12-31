package com.sarathexp.offlinewallet.presentation.screen.card.interactor

import com.sarathexp.offlinewallet.app.base.UIState
import com.sarathexp.offlinewallet.domain.model.card.CardListItem
import com.sarathexp.offlinewallet.domain.model.card.CardNetwork
import kotlinx.collections.immutable.ImmutableList

data class CardUIState(
    val searchText: String,
    val cards: ImmutableList<CardListItem>,

    val cardNumber: String,
    val cardHolder: String,
    val cardExpiry: String,
    val cardCvv: String,
    val cardNetwork: CardNetwork,

): UIState
