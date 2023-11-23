package com.sarathexp.offlinewallet.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sarathexp.offlinewallet.domain.model.auth.AuthItem
import java.time.LocalDateTime

@Entity(
    tableName = "auth_history"
)
data class AuthHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val authItemId: Long,
    val authItem: AuthItem,
    val authenticatedAt: LocalDateTime = LocalDateTime.now()
)
