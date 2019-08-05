package com.kalashnyk.denys.retrofitexample;

import android.app.Application;

import com.kalashnyk.denys.retrofitexample.di.component.DaggerUserApiComponent;
import com.kalashnyk.denys.retrofitexample.di.component.UserApiComponent;
import com.kalashnyk.denys.retrofitexample.di.module.UserApiModule;

public class App extends Application {

    UserApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    private void initDagger() {
        mApiComponent = DaggerUserApiComponent.builder()
                .userApiModule(new UserApiModule())
                .build();
    }

    public UserApiComponent getApiComponent() {
        return mApiComponent;
    }
}

