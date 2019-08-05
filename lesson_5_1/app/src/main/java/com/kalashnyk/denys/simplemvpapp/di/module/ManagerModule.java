package com.kalashnyk.denys.simplemvpapp.di.module;

import com.kalashnyk.denys.simplemvpapp.data.database.UserDao;
import com.kalashnyk.denys.simplemvpapp.data.managers.UserManager;
import com.kalashnyk.denys.simplemvpapp.data.network.ServerCommunicator;

import dagger.Module;
import dagger.Provides;

@Module
public class ManagerModule {

    @Provides
    public UserManager provideUserManager(ServerCommunicator communicator, UserDao DAOuser) {
        return new UserManager(DAOuser, communicator);
    }
}

