package com.kalashnyk.denys.simplemvpapp.di.component;

import com.kalashnyk.denys.simplemvpapp.di.module.PresenterModule;
import com.kalashnyk.denys.simplemvpapp.di.scope.PresenterScope;
import com.kalashnyk.denys.simplemvpapp.presentation.activity.detail.DetailActivity;
import com.kalashnyk.denys.simplemvpapp.presentation.activity.main.MainActivity;

import dagger.Component;

@PresenterScope
@Component(modules = {PresenterModule.class}, dependencies = {InteractorComponent.class})
public interface PresenterComponent {
    void inject(DetailActivity activity);
    void inject(MainActivity activity);
}
