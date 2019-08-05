package com.kalashnyk.denys.simplemvpapp.di.component;

import com.kalashnyk.denys.simplemvpapp.data.network.ServerCommunicator;
import com.kalashnyk.denys.simplemvpapp.di.module.ApiModule;
import com.kalashnyk.denys.simplemvpapp.di.scope.ApiScope;

import dagger.Component;

@ApiScope
@Component(modules = {ApiModule.class})
public interface ApiComponent {
    ServerCommunicator getUserServerCommunicator();
}
