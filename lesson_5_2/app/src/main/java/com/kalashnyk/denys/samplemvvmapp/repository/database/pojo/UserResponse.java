package com.kalashnyk.denys.samplemvvmapp.repository.database.pojo;

import com.google.gson.annotations.SerializedName;
import com.kalashnyk.denys.samplemvvmapp.repository.database.entity.UserEntity;

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
