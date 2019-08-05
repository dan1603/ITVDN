package com.kalashnyk.denys.simplemvpapp.presentation.activity.main;

import com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity;
import com.kalashnyk.denys.simplemvpapp.presentation.base.BaseContract;

import java.util.List;

public interface MainContract {

    interface View extends BaseContract.View {
        void showUsers(List<UserEntity> users);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getUsers();
    }

}

