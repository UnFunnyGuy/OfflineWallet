package com.sarathexp.offlinewallet.domain.use_case.card

data class CardUseCases(
    val addCard: AddCard,
    val updateCard: UpdateCard,
    val deleteCard: DeleteCard,
    val deleteCardById: DeleteCardById,
    val getCardById: GetCardById,
    val getAllCards: GetAllCards
)
