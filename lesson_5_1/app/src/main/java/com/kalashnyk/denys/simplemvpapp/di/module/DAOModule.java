package com.kalashnyk.denys.simplemvpapp.di.module;

import com.kalashnyk.denys.simplemvpapp.data.database.UserDao;

import dagger.Module;
import dagger.Provides;

@Module
public class DAOModule {
    @Provides
    UserDao daoUser() {
        return new UserDao();
    }
}
