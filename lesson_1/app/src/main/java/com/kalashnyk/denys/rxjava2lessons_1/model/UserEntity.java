package com.kalashnyk.denys.rxjava2lessons_1.model;

public class UserEntity {

    private int mId;
    private String mFirstName;
    private String mLastName;
    private int mGender;

    public UserEntity() {}

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public int getGender() {
        return mGender;
    }

    public void setGender(int gender) {
        mGender = gender;
    }
}
