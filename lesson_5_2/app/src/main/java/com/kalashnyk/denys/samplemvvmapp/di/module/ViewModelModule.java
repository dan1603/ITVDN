package com.kalashnyk.denys.samplemvvmapp.di.module;

import android.app.Application;

import com.kalashnyk.denys.App;
import com.kalashnyk.denys.samplemvvmapp.di.scope.ViewModelScope;
import com.kalashnyk.denys.samplemvvmapp.domain.AllUsersViewModel;
import com.kalashnyk.denys.samplemvvmapp.domain.SingleUserViewModel;
import com.kalashnyk.denys.samplemvvmapp.repository.AppRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {

    Application mApp;

    public ViewModelModule(App app) {
        mApp = app;
    }

    @ViewModelScope
    @Provides
    AllUsersViewModel providesAllUserViewModel(AppRepository repository) {
        return new AllUsersViewModel(mApp, repository);
    }

    @ViewModelScope
    @Provides
    SingleUserViewModel providesSingleUserViewModel(AppRepository repository) {
        return new SingleUserViewModel(mApp, repository);
    }
}
