package com.kalashnyk.denys.gmapsapp.repository.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.kalashnyk.denys.gmapsapp.repository.database.entity.PinEntity;

import java.util.List;

@Dao
public interface PinDao {
    @Query("SELECT * FROM pins")
    List<PinEntity> getAll();

    @Query("SELECT * FROM pins WHERE mId = :mId")
    PinEntity getById(int mId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<PinEntity> list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void update(PinEntity pinEntity);

    @Update
    void updateAll(List<PinEntity> list);

    @Delete
    void delete(PinEntity pinEntity);
}
