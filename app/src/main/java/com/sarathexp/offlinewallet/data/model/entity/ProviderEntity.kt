package com.sarathexp.offlinewallet.data.model.entity

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "providers")
data class ProviderEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val isBank: Boolean,
    val issuesCards: Boolean,
    val drawableId: String,
    val logoRes: Int? = null,
    val iconRes: Int? = null
) {
    companion object {
        fun providersFromJsonString(jsonString: String): List<ProviderEntity> {
            val typeToken = object : TypeToken<List<ProviderEntity>>() {}.type
            return Gson().fromJson(jsonString, typeToken)
        }
    }
}

//Only runs on json changes, and i think it wont affect that much!!
fun List<ProviderEntity>.setDrawableId(context: Context): List<ProviderEntity> {
    val resource = context.resources
    return this.map { entity ->
        entity.copy(
            logoRes =
                resource.getIdentifier(
                    "${entity.drawableId}_logo",
                    "drawable",
                    context.packageName
                ),
            iconRes =
                resource.getIdentifier(
                    "${entity.drawableId}_icon",
                    "drawable",
                    context.packageName
                ),
        )
    }
}
