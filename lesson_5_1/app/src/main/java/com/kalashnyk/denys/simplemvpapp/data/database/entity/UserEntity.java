package com.kalashnyk.denys.simplemvpapp.data.database.entity;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
public class UserEntity extends RealmObject {
    @SerializedName("id")
    @PrimaryKey
    private Long mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("surname")
    private String mSurname;

    @SerializedName("fathername")
    private String mFathername;

    public UserEntity() { }

    @Override
    public String toString() {
        return "UserEntity{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mFathername='" + mFathername + '\'' +
                '}';
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String mSurname) {
        this.mSurname = mSurname;
    }

    public String getFathername() {
        return mFathername;
    }

    public void setFathername(String mFathername) {
        this.mFathername = mFathername;
    }
}
