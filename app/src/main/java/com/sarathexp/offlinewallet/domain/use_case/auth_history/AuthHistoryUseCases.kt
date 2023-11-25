package com.sarathexp.offlinewallet.domain.use_case.auth_history

data class AuthHistoryUseCases(
    val addAuthHistory: AddAuthHistory,
    val deleteAuthHistoryById: DeleteAuthHistoryById,
    val getAuthHistoryById: GetAuthHistoryById,
    val getAllAuthHistories: GetAllAuthHistories,
)