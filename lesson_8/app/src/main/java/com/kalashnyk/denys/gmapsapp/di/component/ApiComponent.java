package com.kalashnyk.denys.gmapsapp.di.component;

import com.kalashnyk.denys.gmapsapp.di.module.ApiModule;
import com.kalashnyk.denys.gmapsapp.di.scope.ApiScope;
import com.kalashnyk.denys.gmapsapp.repository.server.ServerCommunicator;

import dagger.Component;

@ApiScope
@Component(modules = {ApiModule.class})
public interface ApiComponent {
    ServerCommunicator getServerCommunicator();
}
