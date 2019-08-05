package com.kalashnyk.denys.simplemvpapp.data.pojo.response;

import com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("records")
    private List<UserEntity> mRecords;

    public List<UserEntity> getRecords() {
        return mRecords;
    }

    public void setRecords(List<UserEntity> mRecords) {
        this.mRecords = mRecords;
    }
}
