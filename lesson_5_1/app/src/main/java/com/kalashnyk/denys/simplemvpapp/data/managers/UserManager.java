package com.kalashnyk.denys.simplemvpapp.data.managers;

import com.kalashnyk.denys.simplemvpapp.data.base.BaseThrowable;
import com.kalashnyk.denys.simplemvpapp.data.base.NetworkUser;
import com.kalashnyk.denys.simplemvpapp.data.database.UserDao;
import com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity;
import com.kalashnyk.denys.simplemvpapp.data.network.ServerCommunicator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import io.reactivex.Single;
import okhttp3.ResponseBody;

public class UserManager {

    private UserDao mUserDAO;
    private ServerCommunicator mCommunicator;

    public UserManager(UserDao userDao, ServerCommunicator communicator) {
        this.mUserDAO = userDao;
        this.mCommunicator = communicator;
    }

    public Single<List<UserEntity>> getUsers() {
        List<UserEntity> savedUsers = mUserDAO.getAllUsers();
        return (savedUsers != null && !savedUsers.isEmpty())
                ? Single.just(savedUsers)
                : mCommunicator.getAllUsers()
                .flatMap(usersResponse -> {
                    if (usersResponse.isSuccessful()) {
                        mUserDAO.saveAllUsers(Objects.requireNonNull(usersResponse.body()).getRecords());
                        return Single.just((usersResponse.body().getRecords()));
                    } else {
                        NetworkUser networkError = NetworkUser.getNetworkByCode(usersResponse.code());
                        String message = networkError != null
                                ? networkError.toString()
                                : getErrorMessage(Objects.requireNonNull(usersResponse.errorBody()));
                        return Single.error(new BaseThrowable(message, usersResponse.code()));
                    }
                });
    }

    public Single<UserEntity> getUserById(int id) {
        UserEntity user = mUserDAO.getUserById(id);
        return Single.just(user);
    }

    private String getErrorMessage(ResponseBody responseBody) throws JSONException, IOException {
        JSONObject jsonObject = new JSONObject(responseBody.string());
        JSONObject error = jsonObject.getJSONObject("error");
        return error.getString("message");
    }
}
