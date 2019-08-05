package com.kalashnyk.denys.kotlinbindingsample.repository.server

import com.kalashnyk.denys.kotlinbindingsample.repository.database.entity.UserEntity
import com.kalashnyk.denys.kotlinbindingsample.repository.database.pojo.UserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/android/rest/api-v2.php/records/users3")
    fun getUsers(): Single<Response<UserResponse>>

    @GET("/api/android/rest/api-v2.php/records/users3/{id}")
    fun getUserById(@Path("id") id: Int): Single<UserEntity>
}
