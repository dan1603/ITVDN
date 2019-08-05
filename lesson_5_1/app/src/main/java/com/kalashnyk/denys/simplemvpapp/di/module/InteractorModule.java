package com.kalashnyk.denys.simplemvpapp.di.module;

import com.kalashnyk.denys.simplemvpapp.data.managers.UserManager;
import com.kalashnyk.denys.simplemvpapp.di.scope.InteractorScope;
import com.kalashnyk.denys.simplemvpapp.domain.UserInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {

    @Provides
    @InteractorScope
    public UserInteractor provideUserInteractor(UserManager manager) {
        return new UserInteractor(manager);
    }
}
