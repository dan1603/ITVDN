package com.kalashnyk.denys.simplemvpapp.data.base;

public enum NetworkUser {

    VALIDATION_ERROR("Error", 400);

    private final String mName;
    private final int mCode;

    NetworkUser(String s, Integer c) {
        mName = s;
        mCode = c;
    }

    public static NetworkUser getNetworkByCode(int code) {
        for (NetworkUser networkError : NetworkUser.values()) {
            if (networkError.mCode == code)
                return networkError;
        }
        return null;
    }

    public boolean equalsName(String otherName) {
        return mName.equals(otherName);
    }

    public String toString() {
        return this.mName;
    }
    public Integer getCode() {
        return mCode;
    }
}
