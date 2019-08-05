package com.kalashnyk.denys.samplemvvmapp.repository.server;

import com.kalashnyk.denys.samplemvvmapp.repository.database.entity.UserEntity;
import com.kalashnyk.denys.samplemvvmapp.repository.database.pojo.UserResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/api/android/rest/api-v2.php/records/users")
    Single<Response<UserResponse>> getUsers();

    @GET("/api/android/rest/api-v2.php/records/users/{id}")
    Single<UserEntity> getUserById(@Path("id") int id);

}
