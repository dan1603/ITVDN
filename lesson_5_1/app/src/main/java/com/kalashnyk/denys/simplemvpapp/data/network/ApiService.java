package com.kalashnyk.denys.simplemvpapp.data.network;

import com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity;
import com.kalashnyk.denys.simplemvpapp.data.pojo.response.UserResponse;

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
