package com.kalashnyk.denys.gmapsapp.di.module;

import android.app.Application;

import com.kalashnyk.denys.App;
import com.kalashnyk.denys.gmapsapp.di.scope.ViewModelScope;
import com.kalashnyk.denys.gmapsapp.domain.AllPinsViewModel;
import com.kalashnyk.denys.gmapsapp.domain.SinglePinViewModel;
import com.kalashnyk.denys.gmapsapp.repository.AppRepository;

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
    AllPinsViewModel providesAllPinsViewModel(AppRepository repository) {
        return new AllPinsViewModel(mApp, repository);
    }

    @ViewModelScope
    @Provides
    SinglePinViewModel providesSinglePinViewModel(AppRepository repository) {
        return new SinglePinViewModel(mApp, repository);
    }
}
