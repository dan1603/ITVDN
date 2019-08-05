package com.kalashnyk.denys.gmapsapp.repository.server;

import com.kalashnyk.denys.gmapsapp.repository.database.entity.PinEntity;
import com.kalashnyk.denys.gmapsapp.repository.database.pojo.PinsResponse;

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

    public Single<Response<PinsResponse>> getAllPins(){
        return mService.getPins();
    }

    public Single<PinEntity> getPin(int id){
        return mService.getPinById(id);
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
