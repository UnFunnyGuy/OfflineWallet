package com.sarathexp.offlinewallet.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "providers")
data class ProviderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val isBank: Boolean,
    val issuesCards: Boolean,
    val logoDrawableRes: String?,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
