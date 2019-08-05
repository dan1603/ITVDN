package com.kalashnyk.denys.samplemvvmapp.di.component;

import com.kalashnyk.denys.samplemvvmapp.di.module.ApiModule;
import com.kalashnyk.denys.samplemvvmapp.di.scope.ApiScope;
import com.kalashnyk.denys.samplemvvmapp.repository.server.ServerCommunicator;

import dagger.Component;

@ApiScope
@Component(modules = {ApiModule.class})
public interface ApiComponent {
    ServerCommunicator getServerCommunicator();
}
