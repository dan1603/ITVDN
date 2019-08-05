package com.kalashnyk.denys.samplemvvmapp.domain;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.kalashnyk.denys.samplemvvmapp.presentation.widget.SingleLiveEvent;
import com.kalashnyk.denys.samplemvvmapp.repository.AppRepository;
import com.kalashnyk.denys.samplemvvmapp.repository.database.entity.UserEntity;

import java.util.List;

public class AllUsersViewModel extends BaseViewModel {

    private AppRepository mRepository;
    private SingleLiveEvent<List<UserEntity>> liveDataItems = new SingleLiveEvent<List<UserEntity>>();

    public AllUsersViewModel(Application application, AppRepository repository) {
        super(application);
        mRepository = repository;
    }

    @SuppressLint("CheckResult")
    public void getAllItems() {
        mRepository.getAllUsers().subscribe(list -> liveDataItems.setValue(list));
    }

    public LiveData<List<UserEntity>> getLiveDataItems() {
        return liveDataItems;
    }
}
