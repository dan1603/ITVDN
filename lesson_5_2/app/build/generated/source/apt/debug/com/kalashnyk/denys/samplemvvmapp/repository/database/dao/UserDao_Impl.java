package com.kalashnyk.denys.samplemvvmapp.repository.database.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.kalashnyk.denys.samplemvvmapp.repository.database.entity.UserEntity;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfUserEntity;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfUserEntity;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfUserEntity;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserEntity = new EntityInsertionAdapter<UserEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `users`(`mId`,`mName`,`mSurname`,`mFathername`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getSurname() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSurname());
        }
        if (value.getFathername() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFathername());
        }
      }
    };
    this.__deletionAdapterOfUserEntity = new EntityDeletionOrUpdateAdapter<UserEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `users` WHERE `mId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfUserEntity = new EntityDeletionOrUpdateAdapter<UserEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `users` SET `mId` = ?,`mName` = ?,`mSurname` = ?,`mFathername` = ? WHERE `mId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getSurname() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSurname());
        }
        if (value.getFathername() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFathername());
        }
        stmt.bindLong(5, value.getId());
      }
    };
  }

  @Override
  public void insertList(List<UserEntity> list) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserEntity.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(UserEntity userEntity) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserEntity.insert(userEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(UserEntity userEntity) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfUserEntity.handle(userEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateAll(List<UserEntity> list) {
    __db.beginTransaction();
    try {
      __updateAdapterOfUserEntity.handleMultiple(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<UserEntity> getAll() {
    final String _sql = "SELECT * FROM users";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMId = _cursor.getColumnIndexOrThrow("mId");
      final int _cursorIndexOfMName = _cursor.getColumnIndexOrThrow("mName");
      final int _cursorIndexOfMSurname = _cursor.getColumnIndexOrThrow("mSurname");
      final int _cursorIndexOfMFathername = _cursor.getColumnIndexOrThrow("mFathername");
      final List<UserEntity> _result = new ArrayList<UserEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final UserEntity _item;
        final int _tmpMId;
        _tmpMId = _cursor.getInt(_cursorIndexOfMId);
        final String _tmpMName;
        _tmpMName = _cursor.getString(_cursorIndexOfMName);
        final String _tmpMSurname;
        _tmpMSurname = _cursor.getString(_cursorIndexOfMSurname);
        final String _tmpMFathername;
        _tmpMFathername = _cursor.getString(_cursorIndexOfMFathername);
        _item = new UserEntity(_tmpMId,_tmpMName,_tmpMSurname,_tmpMFathername);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public UserEntity getById(int mId) {
    final String _sql = "SELECT * FROM users WHERE mId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, mId);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMId = _cursor.getColumnIndexOrThrow("mId");
      final int _cursorIndexOfMName = _cursor.getColumnIndexOrThrow("mName");
      final int _cursorIndexOfMSurname = _cursor.getColumnIndexOrThrow("mSurname");
      final int _cursorIndexOfMFathername = _cursor.getColumnIndexOrThrow("mFathername");
      final UserEntity _result;
      if(_cursor.moveToFirst()) {
        final int _tmpMId;
        _tmpMId = _cursor.getInt(_cursorIndexOfMId);
        final String _tmpMName;
        _tmpMName = _cursor.getString(_cursorIndexOfMName);
        final String _tmpMSurname;
        _tmpMSurname = _cursor.getString(_cursorIndexOfMSurname);
        final String _tmpMFathername;
        _tmpMFathername = _cursor.getString(_cursorIndexOfMFathername);
        _result = new UserEntity(_tmpMId,_tmpMName,_tmpMSurname,_tmpMFathername);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
