package com.kalashnyk.denys.samplemvvmapp.repository;

import com.kalashnyk.denys.samplemvvmapp.repository.database.dao.UserDao;
import com.kalashnyk.denys.samplemvvmapp.repository.database.entity.UserEntity;
import com.kalashnyk.denys.samplemvvmapp.repository.server.ServerCommunicator;

import java.util.List;
import java.util.Objects;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppRepository {
    private ServerCommunicator mServerCommunicator;
    private UserDao mDatabase;

    public AppRepository(ServerCommunicator serverCommunicator, UserDao database) {
        mServerCommunicator = serverCommunicator;
        mDatabase = database;
    }

    public Single<List<UserEntity>> getAllUsers() {
        return mServerCommunicator.getAllUsers()
                .flatMap(list -> {
                    mDatabase.insertList(Objects.requireNonNull(list.body()).getRecords());
                    return Single.just(mDatabase.getAll());
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<UserEntity> getUser(int id) {
        return mServerCommunicator.getUser(id)
                .map(userEntity -> {
                    UserEntity user = mDatabase.getById(id);
                    return user != null ? user : user;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
