package com.kalashnyk.denys.gmapsapp.di.module;

import com.kalashnyk.denys.gmapsapp.di.scope.RepositoryScope;
import com.kalashnyk.denys.gmapsapp.repository.AppRepository;
import com.kalashnyk.denys.gmapsapp.repository.database.dao.PinDao;
import com.kalashnyk.denys.gmapsapp.repository.server.ServerCommunicator;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @RepositoryScope
    @Provides
    AppRepository providesRepository(ServerCommunicator communicator, PinDao productDAO) {
        return new AppRepository(communicator, productDAO);
    }
}
