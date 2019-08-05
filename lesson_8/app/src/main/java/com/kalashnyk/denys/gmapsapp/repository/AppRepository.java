package com.kalashnyk.denys.gmapsapp.repository;

import com.kalashnyk.denys.gmapsapp.repository.database.dao.PinDao;
import com.kalashnyk.denys.gmapsapp.repository.database.entity.PinEntity;
import com.kalashnyk.denys.gmapsapp.repository.server.ServerCommunicator;

import java.util.List;
import java.util.Objects;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppRepository {
    private ServerCommunicator mServerCommunicator;
    private PinDao mDatabase;

    public AppRepository(ServerCommunicator serverCommunicator, PinDao database) {
        mServerCommunicator = serverCommunicator;
        mDatabase = database;
    }

    public Single<List<PinEntity>> getAllPins() {
        return mServerCommunicator.getAllPins()
                .flatMap(list -> {
                    mDatabase.insertList(Objects.requireNonNull(list.body()).getRecords());
                    return Single.just(mDatabase.getAll());
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<PinEntity> getPin(int id) {
        return mServerCommunicator.getPin(id)
                .map(userEntity -> {
                    PinEntity user = mDatabase.getById(id);
                    return user != null ? user : user;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
