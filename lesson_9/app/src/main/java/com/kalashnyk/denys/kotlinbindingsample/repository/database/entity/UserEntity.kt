package com.kalashnyk.denys.kotlinbindingsample.repository.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("fathername")
    val fathername: String,
    @SerializedName("avatar")
    val avatar: String
)
{
    fun getTag(): String {
        return id.toString()
    }
}