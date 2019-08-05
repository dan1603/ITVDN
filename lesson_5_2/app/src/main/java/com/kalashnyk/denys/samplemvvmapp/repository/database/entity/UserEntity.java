package com.kalashnyk.denys.samplemvvmapp.repository.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "users")
public class UserEntity {
    @PrimaryKey
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("surname")
    private String mSurname;
    @SerializedName("fathername")
    private String mFathername;

    public UserEntity(int mId, String mName, String mSurname, String mFathername) {
        setId(mId);
        setName(mName);
        setSurname(mSurname);
        setFathername(mFathername);
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
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

    @Override
    public String toString() {
        return "ProductEntity{" +
                "mId=" + getId() +
                ", mName='" + getName() + '\'' +
                ", mSurname='" + getSurname() + '\'' +
                ", mFathername='" + getFathername() + '\'' +
                '}';
    }
}
