package com.sarathexp.offlinewallet.domain.model.card

enum class CardNetwork{
    Visa,
    MasterCard,
    Rupay,
    AmEx,
    DinersClub,
    Unknown,
    Other;

    companion object {
        val allNetworks: List<CardNetwork>
            get() = enumValues<CardNetwork>().toList()
    }
}