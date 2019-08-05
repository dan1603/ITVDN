package com.kalashnyk.denys.gmapsapp.domain;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.kalashnyk.denys.gmapsapp.presentation.widget.SingleLiveEvent;
import com.kalashnyk.denys.gmapsapp.repository.AppRepository;
import com.kalashnyk.denys.gmapsapp.repository.database.entity.PinEntity;

import java.util.List;

public class AllPinsViewModel extends BaseViewModel {

    private AppRepository mRepository;
    private SingleLiveEvent<List<PinEntity>> liveDataItems = new SingleLiveEvent<>();

    public AllPinsViewModel(Application application, AppRepository repository) {
        super(application);
        mRepository = repository;
    }

    @SuppressLint("CheckResult")
    public void getAllItems() {
        mRepository.getAllPins().subscribe(list -> liveDataItems.setValue(list), throwable -> {
            Log.d("CheckId" , "throwable - " + throwable.getMessage());
        });
    }

    public LiveData<List<PinEntity>> getLiveDataItems() {
        return liveDataItems;
    }
}
