package com.sarathexp.offlinewallet.presentation.screen.card

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sarathexp.offlinewallet.R
import com.sarathexp.offlinewallet.app.base.collectState
import com.sarathexp.offlinewallet.presentation.component.NormalNumberTextField
import com.sarathexp.offlinewallet.presentation.component.NormalTextField
import com.sarathexp.offlinewallet.presentation.component.TypeInputTransformer
import com.sarathexp.offlinewallet.presentation.composable.TitleBarScreen
import com.sarathexp.offlinewallet.presentation.screen.card.interactor.CardUIAction
import com.sarathexp.offlinewallet.presentation.screen.card.interactor.CardUIState
import com.sarathexp.offlinewallet.presentation.screen.card.interactor.CardViewModel
import com.sarathexp.offlinewallet.presentation.util.CardTransformation
import com.sarathexp.offlinewallet.presentation.util.InputEvent
import com.sarathexp.offlinewallet.presentation.util.TypeInputEvent
import com.sarathexp.offlinewallet.util.extension.formattedString

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

//TODO:
@Composable
private fun ColumnScope.Content(
    state: CardUIState,
    onAction: (CardUIAction) -> Unit
) {

    NormalNumberTextField(
        label = R.string.field_card_number,
        transformer =
            TypeInputTransformer(
                transform = { it.formattedString },
                reverseTransform = { it.toLongOrNull() ?: 0L }
            ),
        inputEvent =
            TypeInputEvent(state.cardNumber) { onAction(CardUIAction.CardNumberChanged(it)) },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = CardTransformation.normalCards(),
    )

    NormalTextField(
        label = R.string.field_card_number,
        inputEvent = InputEvent(state.cardHolder) { onAction(CardUIAction.CardHolderChanged(it.uppercase())) },
        modifier = Modifier.fillMaxWidth(),
    )
}
