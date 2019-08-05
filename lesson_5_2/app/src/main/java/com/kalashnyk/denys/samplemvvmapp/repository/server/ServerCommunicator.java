package com.kalashnyk.denys.samplemvvmapp.repository.server;

import android.content.Context;

import com.kalashnyk.denys.samplemvvmapp.repository.database.entity.UserEntity;
import com.kalashnyk.denys.samplemvvmapp.repository.database.pojo.UserResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.CompletableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ServerCommunicator {
    private static final int DEFAULT_TIMEOUT = 10;
    private static final int DEFAULT_RETRY_ATTEMPTS = 4;
    private ApiService mService;

    public ServerCommunicator(ApiService service) {
        mService = service;
    }

    public Single<Response<UserResponse>> getAllUsers(){
        return mService.getUsers();
    }

    public Single<UserEntity> getUser(int id){
        return mService.getUserById(id);
    }

    private static <T> SingleTransformer<T, T> singleTransformer() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .timeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retry(DEFAULT_RETRY_ATTEMPTS);
    }

    private static <T> ObservableTransformer<T, T> observableTransformer() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .timeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retry(DEFAULT_RETRY_ATTEMPTS);
    }

    private static CompletableTransformer completableTransformer() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .timeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retry(DEFAULT_RETRY_ATTEMPTS);
    }
}
