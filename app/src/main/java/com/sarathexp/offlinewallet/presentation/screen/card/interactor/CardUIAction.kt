package com.sarathexp.offlinewallet.presentation.screen.card.interactor

import com.sarathexp.offlinewallet.app.base.ActionEvent

sealed interface CardUIAction : ActionEvent {

    data class CardNumberChanged(val cardNumber: Long) : CardUIAction
    data class CardHolderChanged(val name: String) : CardUIAction

    data class SetSearchText(val searchText: String) : CardUIAction
    data object AddCard : CardUIAction
    data class DeleteCard(val cardId: Long) : CardUIAction
    data class UpdateCard(val cardId: Long) : CardUIAction
}
