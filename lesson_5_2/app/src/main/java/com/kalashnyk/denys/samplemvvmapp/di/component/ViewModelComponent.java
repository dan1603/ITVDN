package com.kalashnyk.denys.samplemvvmapp.di.component;

import com.kalashnyk.denys.samplemvvmapp.di.module.ViewModelModule;
import com.kalashnyk.denys.samplemvvmapp.di.scope.ViewModelScope;
import com.kalashnyk.denys.samplemvvmapp.presentation.activities.detail.DetailActivity;
import com.kalashnyk.denys.samplemvvmapp.presentation.activities.main.MainActivity;

import dagger.Component;

@ViewModelScope
@Component(modules = {ViewModelModule.class}, dependencies = {RepositoryComponent.class})
public interface ViewModelComponent {
    void inject(MainActivity activity);
    void inject(DetailActivity activity);
}
