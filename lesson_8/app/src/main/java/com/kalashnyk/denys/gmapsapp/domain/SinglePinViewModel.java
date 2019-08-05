package com.kalashnyk.denys.gmapsapp.domain;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.kalashnyk.denys.gmapsapp.presentation.widget.SingleLiveEvent;
import com.kalashnyk.denys.gmapsapp.repository.AppRepository;
import com.kalashnyk.denys.gmapsapp.repository.database.entity.PinEntity;

public class SinglePinViewModel extends BaseViewModel {

    private AppRepository mRepository;
    private SingleLiveEvent<PinEntity> liveDataItems = new SingleLiveEvent<PinEntity>();

    public SinglePinViewModel(Application application, AppRepository repository) {
        super(application);
        mRepository = repository;
    }

    @SuppressLint("CheckResult")
    public void getItem(int id) {
        mRepository.getPin(id).subscribe(userEntity -> liveDataItems.setValue(userEntity), Throwable::printStackTrace);
    }

    public LiveData<PinEntity> getLiveDataItem() {
        return liveDataItems;
    }
}
