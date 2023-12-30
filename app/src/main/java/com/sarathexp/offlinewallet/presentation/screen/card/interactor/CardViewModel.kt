package com.sarathexp.offlinewallet.presentation.screen.card.interactor

import com.sarathexp.offlinewallet.app.base.BaseViewModel
import com.sarathexp.offlinewallet.app.base.launch
import com.sarathexp.offlinewallet.domain.model.card.CardNetwork
import com.sarathexp.offlinewallet.domain.use_case.card.CardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(private val cardUseCases: CardUseCases) :
    BaseViewModel<CardUIState, CardUIEvent, CardUIAction>() {

    override fun initialState(): CardUIState {
        return CardUIState(
            searchText = "",
            cards = persistentListOf(),
            cardNumber = 0L,
            cardHolder = "",
            cardExpiry = 0L,
            cardCvv = null,
            cardNetwork = CardNetwork.MASTER_CARD
        )
    }

    init {
        launch {
            cardUseCases.getAllCards.performStreaming(params = "").collectLatest { cards ->
                update { copy(cards = cards.toImmutableList()) }
            }
        }
    }

    override fun onActionEvent(action: CardUIAction) {
        when (action) {
            is CardUIAction.SetSearchText -> {}
            CardUIAction.AddCard -> TODO()
            is CardUIAction.DeleteCard -> TODO()
            is CardUIAction.UpdateCard -> TODO()
            is CardUIAction.CardNumberChanged -> update { copy(cardNumber = action.cardNumber) }
            is CardUIAction.CardHolderChanged -> {
                if (action.name.length <= 20)
                    update { copy(cardHolder = action.name) }
            }
            is CardUIAction.CardCvvChanged -> {
                if (action.cvv.toString().length <= 3) update { copy(cardCvv = action.cvv) }
            }
            is CardUIAction.CardExpiryChanged -> {
                if (action.expiry.toString().length <= 4) {
                    if (action.expiry.toString().length >= 2) {
                        val month = action.expiry.toString().take(2).toLongOrNull()
                        if (month != null && month <= 12L)
                            update { copy(cardExpiry = action.expiry) }
                    } else {
                        update { copy(cardExpiry = action.expiry) }
                    }
                }
            }
        }
    }
}
