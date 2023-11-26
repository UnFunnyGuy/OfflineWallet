package com.sarathexp.offlinewallet.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        val IS_FIRST_RUN = booleanPreferencesKey("is_first_run")
        val JSON_CHK_SUM = stringPreferencesKey("json_chk_sum")
//        val DEVICE_ID = stringPreferencesKey("device_id")
    }

    val isFirstRun: Boolean =
        dataStore.data.map { preferences -> preferences[IS_FIRST_RUN] ?: true }.equals(true)

    suspend fun saveFirstRun() {
        dataStore.edit { preferences -> preferences[IS_FIRST_RUN] = false }
    }

    suspend fun getJsonChkSum(): String {
        return dataStore.data.map { preferences -> preferences[JSON_CHK_SUM] ?: "" }.first()
    }

    suspend fun saveJsonChkSum(chkSum: String) {
        dataStore.edit { preferences -> preferences[JSON_CHK_SUM] = chkSum }
    }

}
