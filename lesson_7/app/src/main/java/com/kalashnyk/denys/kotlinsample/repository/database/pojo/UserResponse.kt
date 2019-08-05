package com.kalashnyk.denys.kotlinsample.repository.database.pojo

import com.google.gson.annotations.SerializedName
import com.kalashnyk.denys.kotlinsample.repository.database.entity.UserEntity

class UserResponse {
    @SerializedName("records")
    var records: List<UserEntity>? = null
}