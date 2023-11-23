package com.sarathexp.offlinewallet.domain.model.card

enum class CardType {
    DEBIT,
    CREDIT,
    PREPAID,
    GIFT,
    OTHER;

    companion object {
        val allTypes: List<CardType>
            get() = enumValues<CardType>().toList()

    }
}
