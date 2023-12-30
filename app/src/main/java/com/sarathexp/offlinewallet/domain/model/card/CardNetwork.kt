package com.sarathexp.offlinewallet.domain.model.card

enum class CardNetwork(
    val cardName: String
) {
    VISA("Visa"),
    MASTER_CARD("Master Card") {
        override val formattedCardName = "mastercard"
    },
    RUPAY("Rupay"),
    AMERICAN_EXPRESS("American Express"),
    DINNERS_CLUB("Dinners Club"),
    JCB("JCB"),
    MAESTRO("Maestro"),
    DISCOVER("Discover"),
    UNKNOWN("Unknown");

    open val formattedCardName = cardName.lowercase()

    companion object {
        val allNetworks: List<CardNetwork>
            get() = enumValues<CardNetwork>().toList()
    }
}
