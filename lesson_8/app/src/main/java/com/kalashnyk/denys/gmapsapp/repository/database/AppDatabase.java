package com.kalashnyk.denys.gmapsapp.repository.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.kalashnyk.denys.gmapsapp.repository.database.dao.PinDao;
import com.kalashnyk.denys.gmapsapp.repository.database.entity.PinEntity;

@Database(entities = {PinEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PinDao pinDao();
}
