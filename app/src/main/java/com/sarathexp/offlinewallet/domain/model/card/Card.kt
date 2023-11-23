package com.sarathexp.offlinewallet.domain.model.card

import java.time.LocalDateTime

data class Card(
    val id: Long = 0,
    val providerId: Long?,
    val cardNumber: String,
    val isLinkedToBank: Boolean,
    val bankAccountId: Int?,
    val expiryMonth: Byte,
    val expiryYear: Byte,
    val cvv: String,
    val nameOnCard: String,
    val pin: String,
    val alias: String?,
    val cardNetwork: CardNetwork,
    val cardType: CardType,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
){
    init {
        require(cardNumber.length == 16) { "Card number must be 16 digits" }
        require(cvv.length == 3) { "CVV must be 3 digits" }
        require(pin.length > 3) { "PIN must be at least 3 digits" }
        require(nameOnCard.isNotBlank()) { "Name on card must not be blank" }
    }
}
