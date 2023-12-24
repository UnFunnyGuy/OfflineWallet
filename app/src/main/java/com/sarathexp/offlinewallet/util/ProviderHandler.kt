package com.sarathexp.offlinewallet.util

import android.content.Context
import com.sarathexp.offlinewallet.data.local.AppDataStore
import com.sarathexp.offlinewallet.data.local.dao.ProviderDao
import com.sarathexp.offlinewallet.data.model.entity.ProviderEntity
import com.sarathexp.offlinewallet.data.model.entity.setDrawableId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.InputStream
import java.security.MessageDigest

/**
 * Handles provider data updates.
 *
 * @param context The application context.
 */
class ProviderHandler(private val context: Context) {

    private val logger = Timber.tag("ProviderHandler")

    /**
     * The input stream of the providers json file.
     */
    private val providerJsonInputStream = context.assets.open("providers.json")

    private val dataStore = AppDataStore(context.dataStore)
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    /**
     * Checks for updates in providers json and updates the database if necessary.
     *
     * @param providerDao The provider DAO to interact with the database.
     * @throws ProviderUpdateException If an error occurs during provider data update.
     */
    @Throws(ProviderUpdateException::class)
    fun checkProviders(providerDao: ProviderDao) {
        coroutineScope.launch {
            try {
                val checkSum = getChecksum(providerJsonInputStream)
                val savedCheckSum = dataStore.getJsonChkSum()

                if (checkSum != savedCheckSum) {
                    logger.d(
                        "Checksums differ: New Checksum = $checkSum, Saved Checksum = $savedCheckSum"
                    )

                    val providersJsonString =
                        providerJsonInputStream.bufferedReader().use { it.readText() }

                    val providers =
                        ProviderEntity.providersFromJsonString(providersJsonString)
                            .setDrawableId(context)

                    providerDao.withTransaction {
                        // TODO: Handle Relations if any
                        providerDao.clearAll()
                        providerDao.insertAll(providers)
                    }

                    dataStore.saveJsonChkSum(checkSum)
                    logger.d("Data updated and new checksum saved")
                } else {
                    logger.d("No change detected in providers data")
                }
            } catch (e: Exception) {
                logger.e("Error during provider check: $e")
                throw ProviderUpdateException("Failed to update providers", e)
            } finally {
                providerJsonInputStream.close()
            }
        }
    }

    /**
     * Calculates the MD5 checksum of the given input stream.
     *
     * @param inputStream The input stream to calculate the checksum.
     * @return The MD5 checksum of the given input stream.
     */
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

    /**
     * Exception thrown when an error occurs during provider data update.
     *
     * @param message The error message.
     * @param cause The cause of the error.
     */
    class ProviderUpdateException(message: String, cause: Throwable? = null) :
        Exception(message, cause)
}
