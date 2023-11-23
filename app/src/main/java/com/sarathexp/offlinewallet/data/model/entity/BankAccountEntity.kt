package com.sarathexp.offlinewallet.data.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Index
import java.time.LocalDateTime

@Entity(
    tableName = "bank_accounts",
    foreignKeys = [
        ForeignKey(
            entity = ProviderEntity::class,
            parentColumns = ["id"],
            childColumns = ["providerId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index("providerId")
    ]
)
data class BankAccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val bankName: String,
    val providerId: Long,
    val accountHolderName: String,
    val accountNumber: String,
    val ifsc: String,
    val phoneNumber: String?,
    val alias: String?,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)