package com.kalashnyk.denys.simplemvpapp.di.module;

import com.kalashnyk.denys.simplemvpapp.di.scope.PresenterScope;
import com.kalashnyk.denys.simplemvpapp.domain.UserInteractor;
import com.kalashnyk.denys.simplemvpapp.presentation.activity.detail.DetailContract;
import com.kalashnyk.denys.simplemvpapp.presentation.activity.detail.DetailPresenter;
import com.kalashnyk.denys.simplemvpapp.presentation.activity.main.MainContract;
import com.kalashnyk.denys.simplemvpapp.presentation.activity.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @PresenterScope
    public MainContract.Presenter provideMainPresenter(UserInteractor userInteractor) {
        return new MainPresenter(userInteractor);
    }

    @Provides
    @PresenterScope
    public DetailContract.Presenter provideAuthPresenter(UserInteractor userInteractor) {
        return new DetailPresenter(userInteractor);
    }
}