package com.kalashnyk.denys.simplemvpapp.di.component;

import com.kalashnyk.denys.simplemvpapp.di.module.InteractorModule;
import com.kalashnyk.denys.simplemvpapp.di.scope.InteractorScope;
import com.kalashnyk.denys.simplemvpapp.domain.UserInteractor;

import dagger.Component;

@InteractorScope
@Component(modules = {InteractorModule.class}, dependencies = {ManagerComponent.class})
public interface InteractorComponent {
    UserInteractor getUserInteractor();
}
