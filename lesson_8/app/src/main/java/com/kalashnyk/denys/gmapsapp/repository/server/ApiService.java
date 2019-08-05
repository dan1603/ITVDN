package com.kalashnyk.denys.gmapsapp.repository.server;

import com.kalashnyk.denys.gmapsapp.repository.database.entity.PinEntity;
import com.kalashnyk.denys.gmapsapp.repository.database.pojo.PinsResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/api/android/rest/api-v2.php/records/pins")
    Single<Response<PinsResponse>> getPins();

    @GET("/api/android/rest/api-v2.php/records/pins/{id}")
    Single<PinEntity> getPinById(@Path("id") int id);

}
