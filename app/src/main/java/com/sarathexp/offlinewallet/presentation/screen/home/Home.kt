package com.sarathexp.offlinewallet.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sarathexp.offlinewallet.presentation.screen.card.interactor.CardViewModel

@Destination
@Composable
fun Home(
    navigator: DestinationsNavigator,
    viewModel: CardViewModel = hiltViewModel()
) {



}