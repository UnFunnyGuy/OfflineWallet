package com.sarathexp.offlinewallet.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.sarathexp.offlinewallet.data.local.AppDataStore
import com.sarathexp.offlinewallet.util.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext app: Context): DataStore<Preferences> = app.dataStore

    @Provides
    @Singleton
    fun providePreferenceDataStore(dataStore: DataStore<Preferences>): AppDataStore =
        AppDataStore(dataStore)
}
