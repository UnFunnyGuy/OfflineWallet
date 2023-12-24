package com.sarathexp.offlinewallet.presentation.screen.card

import android.widget.Toast
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sarathexp.offlinewallet.R
import com.sarathexp.offlinewallet.app.base.UIEventHandler
import com.sarathexp.offlinewallet.app.base.collectState
import com.sarathexp.offlinewallet.presentation.composable.TitleBarScreen
import com.sarathexp.offlinewallet.presentation.screen.card.interactor.CardUIAction
import com.sarathexp.offlinewallet.presentation.screen.card.interactor.CardUIEvent
import com.sarathexp.offlinewallet.presentation.screen.card.interactor.CardUIState
import com.sarathexp.offlinewallet.presentation.screen.card.interactor.CardViewModel
import com.sarathexp.offlinewallet.presentation.screen.destinations.AddCardDestination
import com.sarathexp.offlinewallet.presentation.util.isScrollingUp

@Destination
@Composable
fun ListCards(
    navigator: DestinationsNavigator,
    viewModel: CardViewModel
) {

    val context = LocalContext.current
    val cardsListState = rememberLazyListState()
    val state by viewModel.collectState()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { navigator.navigate(AddCardDestination) },
                text = { Text(stringResource(R.string.button_add)) },
                icon = { Icon(imageVector = Icons.Rounded.Add, contentDescription = null) },
                expanded = cardsListState.isScrollingUp()
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { padding ->
        TitleBarScreen(
            title = R.string.screen_title_cards,
            modifier = Modifier.fillMaxSize().padding(padding),
        ) {
            Content(
                cardsListState = cardsListState,
                state = state,
                onAction = viewModel::onActionEvent
            )
        }
    }

    viewModel.UIEventHandler { event ->
        when (event) {
            is CardUIEvent.NavigateUp -> navigator.navigateUp()
            is CardUIEvent.ShowAlert ->
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            else -> {}
        }
    }
}

//TODO:
@Composable
private fun ColumnScope.Content(
    cardsListState: LazyListState,
    state: CardUIState,
    onAction: (CardUIAction) -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = cardsListState
    ) {
        items(
            items = state.cards
        ) {
            Text(text = it.cardNumber)
        }
    }
}
