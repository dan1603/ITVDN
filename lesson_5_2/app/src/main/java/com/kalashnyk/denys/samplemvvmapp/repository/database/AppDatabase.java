package com.kalashnyk.denys.samplemvvmapp.repository.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.kalashnyk.denys.samplemvvmapp.repository.database.dao.UserDao;
import com.kalashnyk.denys.samplemvvmapp.repository.database.entity.UserEntity;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
