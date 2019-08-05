package com.kalashnyk.denys.simplemvpapp.data.network;

import com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity;
import com.kalashnyk.denys.simplemvpapp.data.pojo.response.UserResponse;

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

    public Single<Response<UserResponse>> getAllUsers(){
        return mService.getUsers().compose(singleTransformer());
    }

    public Single<UserEntity> getUserById(int id) {
        return mService.getUserById(id).compose(singleTransformer());
    }

}
