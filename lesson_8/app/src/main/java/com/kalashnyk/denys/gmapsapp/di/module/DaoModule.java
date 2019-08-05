package com.kalashnyk.denys.gmapsapp.di.module;

import com.kalashnyk.denys.gmapsapp.repository.database.AppDatabase;
import com.kalashnyk.denys.gmapsapp.repository.database.dao.PinDao;

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
    PinDao providesProductDao(AppDatabase database) {
        return database.pinDao();
    }
}
