package com.sarathexp.offlinewallet.data.model.mapper

import com.sarathexp.offlinewallet.data.model.entity.ProviderEntity
import com.sarathexp.offlinewallet.domain.model.provider.Provider

fun ProviderEntity.toDomain(): Provider {
    return Provider(
        id = id,
        name = name,
        isBank = isBank,
        issuesCards = issuesCards,
        logoRes = logoRes,
        iconRes = iconRes,
    )
}

