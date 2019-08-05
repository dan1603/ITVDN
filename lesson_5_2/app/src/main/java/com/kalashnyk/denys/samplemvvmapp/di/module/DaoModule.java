package com.kalashnyk.denys.samplemvvmapp.di.module;

import com.kalashnyk.denys.samplemvvmapp.repository.database.AppDatabase;
import com.kalashnyk.denys.samplemvvmapp.repository.database.dao.UserDao;

import dagger.Module;
import dagger.Provides;

@Module
public class DaoModule {

    private AppDatabase mAppDatabase;

    public DaoModule(AppDatabase database) {
        mAppDatabase = database;
    }

    @Provides
    AppDatabase providesRoomDatabase() {
        return mAppDatabase;
    }

    @Provides
    UserDao providesProductDao(AppDatabase database) {
        return database.userDao();
    }
}
