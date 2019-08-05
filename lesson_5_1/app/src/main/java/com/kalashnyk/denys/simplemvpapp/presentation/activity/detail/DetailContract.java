package com.kalashnyk.denys.simplemvpapp.presentation.activity.detail;

import com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity;
import com.kalashnyk.denys.simplemvpapp.presentation.base.BaseContract;

public interface DetailContract {

    interface View extends BaseContract.View {
        void showUser(UserEntity user);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getUser(int id);
    }
}
