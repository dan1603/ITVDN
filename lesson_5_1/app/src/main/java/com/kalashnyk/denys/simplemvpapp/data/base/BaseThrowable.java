package com.kalashnyk.denys.simplemvpapp.data.base;

public class BaseThrowable extends Exception {
    private int mCode;
    public BaseThrowable(String message) {
        super(message);
    }
    public BaseThrowable(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseThrowable(String message, int code) {
        super(message);
        this.mCode = code;
    }

    public boolean isExist() {
        return getCode() == 409;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        this.mCode = code;
    }
}
