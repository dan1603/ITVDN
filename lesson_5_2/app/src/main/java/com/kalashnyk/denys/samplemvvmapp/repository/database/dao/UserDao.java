package com.kalashnyk.denys.samplemvvmapp.repository.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.kalashnyk.denys.samplemvvmapp.repository.database.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<UserEntity> getAll();

    @Query("SELECT * FROM users WHERE mId = :mId")
    UserEntity getById(int mId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<UserEntity> list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void update(UserEntity userEntity);

    @Update
    void updateAll(List<UserEntity> list);

    @Delete
    void delete(UserEntity userEntity);
}
