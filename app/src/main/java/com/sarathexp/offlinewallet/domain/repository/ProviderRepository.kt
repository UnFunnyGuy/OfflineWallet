package com.sarathexp.offlinewallet.domain.repository

import com.sarathexp.offlinewallet.domain.model.provider.Provider
import kotlinx.coroutines.flow.Flow

interface ProviderRepository {
    fun getAll(): Flow<List<Provider>>
}