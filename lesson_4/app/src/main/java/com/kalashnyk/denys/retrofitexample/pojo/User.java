package com.kalashnyk.denys.retrofitexample.pojo;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("surname")
    private String mSurname;

    @SerializedName("fathername")
    private String mFathername;


    public String getFathername() {
        return mFathername;
    }

    public void setFathername(String mFathername) {
        this.mFathername = mFathername;
    }

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String mSurname) {
        this.mSurname = mSurname;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }
}
