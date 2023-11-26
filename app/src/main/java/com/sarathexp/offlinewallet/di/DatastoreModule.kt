package com.sarathexp.offlinewallet.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.sarathexp.offlinewallet.data.local.AppDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    private val Context.dataStore: DataStore<Preferences> by
        preferencesDataStore(name = "preferences")

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext app: Context): DataStore<Preferences> = app.dataStore

    @Provides
    @Singleton
    fun providePreferenceDataStore(dataStore: DataStore<Preferences>): AppDataStore {
        return AppDataStore(dataStore)
    }

}
