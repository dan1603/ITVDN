package com.kalashnyk.denys.gmapsapp.repository.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "pins")
public class PinEntity {
    @PrimaryKey
    @SerializedName("id")
    private int mId;
    @SerializedName("lat")
    private String mLat;
    @SerializedName("lng")
    private String mLng;
    @SerializedName("title")
    private String mTitle;

    public PinEntity(int mId, String mLat, String mLng, String mTitle) {
        setId(mId);
        setLat(mLat);
        setLng(mLng);
        setTitle(mTitle);
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getLat() {
        return mLat;
    }

    public void setLat(String mName) {
        this.mLat = mName;
    }

    public String getLng() { return mLng; }

    public void setLng(String mSurname) {
        this.mLng = mSurname;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mFathername) {
        this.mTitle = mFathername;
    }

    @Override
    public String toString() {
        return "PinEntity{" +
                "mId=" + getId() +
                ", mLat='" + getLat() + '\'' +
                ", mLng='" + getLng() + '\'' +
                ", mTitle='" + getTitle() + '\'' +
                '}';
    }
}
