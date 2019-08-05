package com.kalashnyk.denys;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.kalashnyk.denys.samplemvvmapp.di.component.ApiComponent;
import com.kalashnyk.denys.samplemvvmapp.di.component.DaggerApiComponent;
import com.kalashnyk.denys.samplemvvmapp.di.component.DaggerDaoComponent;
import com.kalashnyk.denys.samplemvvmapp.di.component.DaggerRepositoryComponent;
import com.kalashnyk.denys.samplemvvmapp.di.component.DaggerViewModelComponent;
import com.kalashnyk.denys.samplemvvmapp.di.component.DaoComponent;
import com.kalashnyk.denys.samplemvvmapp.di.component.RepositoryComponent;
import com.kalashnyk.denys.samplemvvmapp.di.component.ViewModelComponent;
import com.kalashnyk.denys.samplemvvmapp.di.module.ApiModule;
import com.kalashnyk.denys.samplemvvmapp.di.module.DaoModule;
import com.kalashnyk.denys.samplemvvmapp.di.module.RepositoryModule;
import com.kalashnyk.denys.samplemvvmapp.di.module.ViewModelModule;
import com.kalashnyk.denys.samplemvvmapp.repository.database.AppDatabase;

public class App extends Application {
    private ViewModelComponent mViewModelComponent;
    private AppDatabase mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        initRoom();
        initDagger();
    }

    private void initRoom() {
        mDatabase = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public ViewModelComponent getViewModelComponent() {
        return mViewModelComponent;
    }

    private void initDagger() {
        ApiComponent apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .build();

        DaoComponent daoComponent = DaggerDaoComponent.builder()
                .daoModule(new DaoModule(mDatabase))
                .build();

        RepositoryComponent repositoryComponent = DaggerRepositoryComponent.builder()
                .apiComponent(apiComponent)
                .daoComponent(daoComponent)
                .repositoryModule(new RepositoryModule())
                .build();

        mViewModelComponent = DaggerViewModelComponent.builder()
                .repositoryComponent(repositoryComponent)
                .viewModelModule(new ViewModelModule(this))
                .build();
    }
}
