package com.kalashnyk.denys.retrofitexample.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

    @SerializedName("records")
    private List<User> mRecords;

    public List<User> getRecords() {
        return mRecords;
    }

    public void setRecords(List<User> mRecords) {
        this.mRecords = mRecords;
    }
}
