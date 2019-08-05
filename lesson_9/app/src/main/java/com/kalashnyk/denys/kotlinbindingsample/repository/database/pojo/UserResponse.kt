package com.kalashnyk.denys.kotlinbindingsample.repository.database.pojo

import com.google.gson.annotations.SerializedName
import com.kalashnyk.denys.kotlinbindingsample.repository.database.entity.UserEntity

class UserResponse {
    @SerializedName("records")
    var records: List<UserEntity>? = null
}