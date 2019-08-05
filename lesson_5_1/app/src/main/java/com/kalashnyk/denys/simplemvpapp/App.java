package com.kalashnyk.denys.simplemvpapp;

import android.app.Application;

import com.kalashnyk.denys.simplemvpapp.di.component.ApiComponent;
import com.kalashnyk.denys.simplemvpapp.di.component.DAOComponent;
import com.kalashnyk.denys.simplemvpapp.di.component.DaggerApiComponent;
import com.kalashnyk.denys.simplemvpapp.di.component.DaggerDAOComponent;
import com.kalashnyk.denys.simplemvpapp.di.component.DaggerInteractorComponent;
import com.kalashnyk.denys.simplemvpapp.di.component.DaggerManagerComponent;
import com.kalashnyk.denys.simplemvpapp.di.component.DaggerPresenterComponent;
import com.kalashnyk.denys.simplemvpapp.di.component.InteractorComponent;
import com.kalashnyk.denys.simplemvpapp.di.component.ManagerComponent;
import com.kalashnyk.denys.simplemvpapp.di.component.PresenterComponent;
import com.kalashnyk.denys.simplemvpapp.di.module.ApiModule;
import com.kalashnyk.denys.simplemvpapp.di.module.DAOModule;
import com.kalashnyk.denys.simplemvpapp.di.module.InteractorModule;
import com.kalashnyk.denys.simplemvpapp.di.module.ManagerModule;
import com.kalashnyk.denys.simplemvpapp.di.module.PresenterModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private PresenterComponent mPresentersComponent;

    public void onCreate() {
        super.onCreate();
        initializeRealm();
        initializeDagger();
    }

    public PresenterComponent getPresentersComponent() {
        return mPresentersComponent;
    }

    private void initializeRealm() {
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("example.realm")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    private void initializeDagger() {
        ApiComponent apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .build();

        DAOComponent daoComponent = DaggerDAOComponent.builder()
                .dAOModule(new DAOModule())
                .build();

        ManagerComponent managersComponent = DaggerManagerComponent.builder()
                .apiComponent(apiComponent)
                .dAOComponent(daoComponent)
                .managerModule(new ManagerModule())
                .build();

        InteractorComponent interactorsComponent = DaggerInteractorComponent.builder()
                .managerComponent(managersComponent)
                .interactorModule(new InteractorModule())
                .build();

        mPresentersComponent = DaggerPresenterComponent.builder()
                .interactorComponent(interactorsComponent)
                .presenterModule(new PresenterModule())
                .build();
    }
}
