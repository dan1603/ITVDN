package com.kalashnyk.denys.samplemvvmapp.di.module;

import com.kalashnyk.denys.samplemvvmapp.di.scope.RepositoryScope;
import com.kalashnyk.denys.samplemvvmapp.repository.AppRepository;
import com.kalashnyk.denys.samplemvvmapp.repository.database.dao.UserDao;
import com.kalashnyk.denys.samplemvvmapp.repository.server.ServerCommunicator;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @RepositoryScope
    @Provides
    AppRepository providesRepository(ServerCommunicator communicator, UserDao productDAO) {
        return new AppRepository(communicator, productDAO);
    }
}
