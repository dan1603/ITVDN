package com.kalashnyk.denys.retrofitexample.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.kalashnyk.denys.retrofitexample.di.scope.AppScope;
import com.kalashnyk.denys.retrofitexample.server.UserAPI;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.kalashnyk.denys.retrofitexample.utils.Constants.BASE_URL;

@Module
public class UserApiModule {

    @Provides
    @AppScope
    public UserAPI provideUserApiService(Retrofit retrofit) {
        return retrofit.create(UserAPI.class);
    }

    @Provides
    @AppScope
    public Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder.baseUrl(BASE_URL).build();
    }

    @Provides
    @AppScope
    public Retrofit.Builder provideRetrofitBuilder() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client);

        return builder;
    }
}
