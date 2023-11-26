package com.sarathexp.offlinewallet.util

import android.content.Context
import com.sarathexp.offlinewallet.data.local.AppDataStore
import com.sarathexp.offlinewallet.data.local.dao.ProviderDao
import com.sarathexp.offlinewallet.data.model.entity.ProviderEntity
import com.sarathexp.offlinewallet.data.model.entity.setDrawableId
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.InputStream
import java.security.MessageDigest
import javax.inject.Inject

private val logger = Timber.tag("ProviderHandler")

class ProviderHandler
@Inject
constructor(@ApplicationContext private val context: Context, private val dataStore: AppDataStore) {

    private val providerJsonInputStream = context.assets.open("providers.json")

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun checkProviders(providerDao: ProviderDao) {
        coroutineScope.launch {
            val checkSum = getChecksum(providerJsonInputStream)
            val savedCheckSum = dataStore.getJsonChkSum()
            if (checkSum != savedCheckSum) {
                logger.d("Checksum:: $checkSum")
                logger.d("Saved Checksum:: $savedCheckSum")
                logger.d("Not Matched")
                val providersJsonString =
                    providerJsonInputStream.bufferedReader().use { it.readText() }
                logger.d("New Data:: $providersJsonString")
                val providers =
                    ProviderEntity.providersFromJsonString(providersJsonString)
                        .setDrawableId(context)
                providerDao.withTransaction {
                    providerDao.clearAll()
                    providerDao.insertAll(providers)
                    logger.d("New Data Inserted")
                }
                dataStore.saveJsonChkSum(checkSum)
                logger.d("New Checksum saved")
            } else {
                logger.d("No Change Detected")
            }
        }
    }

    private fun getChecksum(inputStream: InputStream): String {
        val md = MessageDigest.getInstance("MD5")
        val buffer = ByteArray(8192)
        var bytesRead: Int
        inputStream.mark(0)
        while (inputStream.read(buffer).also { bytesRead = it } > 0) {
            md.update(buffer, 0, bytesRead)
        }
        val digest = md.digest()
        inputStream.reset()

        return digest.joinToString("") { "%02x".format(it) }
    }
}
