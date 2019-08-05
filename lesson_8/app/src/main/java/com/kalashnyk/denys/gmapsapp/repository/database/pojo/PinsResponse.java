package com.kalashnyk.denys.gmapsapp.repository.database.pojo;

import com.google.gson.annotations.SerializedName;
import com.kalashnyk.denys.gmapsapp.repository.database.entity.PinEntity;

import java.util.List;

public class PinsResponse {
    @SerializedName("records")
    private List<PinEntity> mRecords;

    public List<PinEntity> getRecords() {
        return mRecords;
    }

    public void setRecords(List<PinEntity> mRecords) {
        this.mRecords = mRecords;
    }

}
