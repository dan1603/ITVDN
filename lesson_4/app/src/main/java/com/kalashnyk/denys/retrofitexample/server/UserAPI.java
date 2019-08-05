package com.kalashnyk.denys.retrofitexample.server;

import com.kalashnyk.denys.retrofitexample.pojo.User;
import com.kalashnyk.denys.retrofitexample.pojo.UserResponse;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserAPI {

    @GET("/api/android/rest/api-v2.php/records/users")
    Single<UserResponse> getUsers();

    @GET("/api/android/rest/api-v2.php/records/users/{id}")
    Single<User> getUserById(@Path("id") int id);

    @DELETE("/api/android/rest/api-v2.php/records/users/{id}")
    Single<String> deleteUserById(@Path("id") int id);

    @FormUrlEncoded
    @POST("/api/android/rest/api-v1.php/users")
    Single<String> addUser(@Field("name") String name,
                           @Field("surname") String surname,
                           @Field("fathername") String fathername);

    @FormUrlEncoded
    @PUT("/api/android/rest/api-v1.php/users/{id}")
    Single<String> updateUser(@Path("id") int id,
                              @Field("name") String name,
                              @Field("surname") String surname,
                              @Field("fathername") String fathername);

    @Multipart
    @POST("/api/android/rest/api-v1.php/files")
    Single<ResponseBody> uploadFile(@Part MultipartBody.Part file);
}
