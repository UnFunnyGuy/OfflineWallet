package com.sarathexp.offlinewallet.data.repository

import com.sarathexp.offlinewallet.data.local.dao.ProviderDao
import com.sarathexp.offlinewallet.data.model.mapper.toDomain
import com.sarathexp.offlinewallet.domain.model.provider.Provider
import com.sarathexp.offlinewallet.domain.repository.ProviderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProviderRepositoryImpl @Inject constructor(
    private val providerDao: ProviderDao
) : ProviderRepository {

    override fun getAll(): Flow<List<Provider>> {
        return providerDao.getAll().map { list -> list.map { it.toDomain() } }
    }

}
