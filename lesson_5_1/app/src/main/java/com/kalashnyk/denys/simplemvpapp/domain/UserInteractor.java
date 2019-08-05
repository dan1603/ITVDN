package com.kalashnyk.denys.simplemvpapp.domain;

import com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity;
import com.kalashnyk.denys.simplemvpapp.data.managers.UserManager;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserInteractor {
    UserManager mUserManager;
    public UserInteractor(UserManager userManager) {
        mUserManager = userManager;
    }

    public Single<List<UserEntity>> getUsers() {
        return mUserManager
                .getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<UserEntity> getUserById(int id) {
        return mUserManager
                .getUserById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

