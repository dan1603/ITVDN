package com.kalashnyk.denys.simplemvpapp.di.component;

import com.kalashnyk.denys.simplemvpapp.data.managers.UserManager;
import com.kalashnyk.denys.simplemvpapp.di.module.ManagerModule;
import com.kalashnyk.denys.simplemvpapp.di.scope.ManagerScope;

import dagger.Component;

@ManagerScope
@Component(modules = {ManagerModule.class}, dependencies = {ApiComponent.class, DAOComponent.class})
public interface ManagerComponent {
    UserManager getUserManager();
}
