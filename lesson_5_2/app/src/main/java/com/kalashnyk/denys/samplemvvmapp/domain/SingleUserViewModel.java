package com.kalashnyk.denys.samplemvvmapp.domain;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.kalashnyk.denys.samplemvvmapp.presentation.widget.SingleLiveEvent;
import com.kalashnyk.denys.samplemvvmapp.repository.AppRepository;
import com.kalashnyk.denys.samplemvvmapp.repository.database.entity.UserEntity;

public class SingleUserViewModel extends BaseViewModel {

    private AppRepository mRepository;
    private SingleLiveEvent<UserEntity> liveDataItems = new SingleLiveEvent<UserEntity>();

    public SingleUserViewModel(Application application, AppRepository repository) {
        super(application);
        mRepository = repository;
    }

    @SuppressLint("CheckResult")
    public void getItem(int id) {
        mRepository.getUser(id).subscribe(userEntity -> liveDataItems.setValue(userEntity), Throwable::printStackTrace);
    }

    public LiveData<UserEntity> getLiveDataItem() {
        return liveDataItems;
    }
}
