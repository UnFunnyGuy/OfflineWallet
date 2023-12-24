package com.sarathexp.offlinewallet.presentation.screen.card.interactor

import com.sarathexp.offlinewallet.app.base.UIEvent

interface CardUIEvent: UIEvent {
    data object NavigateUp: CardUIEvent
    data class ShowAlert(val message: String): CardUIEvent
}