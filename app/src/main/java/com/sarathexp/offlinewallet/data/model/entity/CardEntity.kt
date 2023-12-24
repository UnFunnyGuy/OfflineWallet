package com.sarathexp.offlinewallet.data.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.sarathexp.offlinewallet.domain.model.card.CardNetwork
import com.sarathexp.offlinewallet.domain.model.card.CardType
import java.time.LocalDateTime

@Entity(
    tableName = "cards",
    foreignKeys = [
        ForeignKey(
            entity = ProviderEntity::class,
            parentColumns = ["id"],
            childColumns = ["providerId"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = BankAccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["bankAccountId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index("providerId"),
        Index("bankAccountId")
    ]
)
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
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
    val color: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
