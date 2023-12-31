package com.sarathexp.offlinewallet.presentation.screen.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sarathexp.offlinewallet.R
import com.sarathexp.offlinewallet.app.base.collectState
import com.sarathexp.offlinewallet.presentation.component.NormalTextField
import com.sarathexp.offlinewallet.presentation.composable.MinimalCard
import com.sarathexp.offlinewallet.presentation.composable.TitleBarScreen
import com.sarathexp.offlinewallet.presentation.screen.card.interactor.CardUIAction
import com.sarathexp.offlinewallet.presentation.screen.card.interactor.CardUIState
import com.sarathexp.offlinewallet.presentation.screen.card.interactor.CardViewModel
import com.sarathexp.offlinewallet.presentation.util.CardTransformation
import com.sarathexp.offlinewallet.presentation.util.InputEvent

@Destination
@Composable
fun AddCard(navigator: DestinationsNavigator, viewModel: CardViewModel) {

    val state by viewModel.collectState()

    Scaffold { padding ->
        TitleBarScreen(
            title = R.string.screen_title_add_cards,
            modifier = Modifier.fillMaxSize().padding(padding),
            onLeftIconClick = { navigator.navigateUp() },
        ) {
            Content(state = state, onAction = viewModel::onActionEvent)
        }
    }
}

// TODO:
@Composable
private fun ColumnScope.Content(state: CardUIState, onAction: (CardUIAction) -> Unit) {

    MinimalCard(
        holder = state.cardHolder,
        number = state.cardNumber,
        expiration = state.cardExpiry,
        cvv = state.cardCvv,
        cardNetwork = state.cardNetwork,
        modifier = Modifier.fillMaxWidth(),
    )

    Spacer(Modifier.height(20.dp))

    // Not Final
    NormalTextField(
        label = R.string.field_card_number,
        inputEvent =
        InputEvent(state.cardNumber) { onAction(CardUIAction.CardNumberChanged(it)) },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = CardTransformation.normalCards(),
        keyboardType = KeyboardType.NumberPassword,
        numberField = true
    )

    NormalTextField(
        label = R.string.field_card_holder,
        inputEvent =
            InputEvent(state.cardHolder) {
                onAction(CardUIAction.CardHolderChanged(it.uppercase()))
            },
        modifier = Modifier.fillMaxWidth(),
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        NormalTextField(
            label = R.string.field_card_expiry,
            inputEvent =
            InputEvent(state.cardExpiry) { onAction(CardUIAction.CardExpiryChanged(it)) },
            modifier = Modifier.weight(1f),
            visualTransformation = CardTransformation.ExpiryDate,
            keyboardType = KeyboardType.NumberPassword,
            numberField = true
        )

        NormalTextField(
            label = R.string.field_card_cvv,
            inputEvent =
            InputEvent(state.cardCvv) { onAction(CardUIAction.CardCvvChanged(it)) },
            modifier = Modifier.weight(1f),
            visualTransformation = CardTransformation.normalCards(),
            keyboardType = KeyboardType.NumberPassword,
            numberField = true
        )
    }
}

