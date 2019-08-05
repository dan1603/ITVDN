package com.kalashnyk.denys.simplemvpapp.data.database;

import com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity;

import java.util.List;

import io.realm.Realm;

public class UserDao extends RealmHelper {

    public void saveAllUsers (List<UserEntity> list){
        executeTransaction(realm -> realm.copyToRealmOrUpdate(list));
    }

    public List<UserEntity> getAllUsers() {
        Realm realm = Realm.getDefaultInstance();
        List<UserEntity> list = realm.where(UserEntity.class).findAll();
        if (list != null) list = realm.copyFromRealm(list);
        return list;
    }

    public UserEntity getUserById(int id) {
        Realm realm = Realm.getDefaultInstance();
        UserEntity user = realm.where(UserEntity.class).equalTo("mId", id).findFirst();
        return user;
    }
}
