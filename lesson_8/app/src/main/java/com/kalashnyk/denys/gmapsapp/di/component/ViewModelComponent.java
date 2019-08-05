package com.kalashnyk.denys.gmapsapp.di.component;

import com.kalashnyk.denys.gmapsapp.di.module.ViewModelModule;
import com.kalashnyk.denys.gmapsapp.di.scope.ViewModelScope;
import com.kalashnyk.denys.gmapsapp.presentation.activities.main.MainActivity;

import dagger.Component;

@ViewModelScope
@Component(modules = {ViewModelModule.class}, dependencies = {RepositoryComponent.class})
public interface ViewModelComponent {
    void inject(MainActivity activity);
}
