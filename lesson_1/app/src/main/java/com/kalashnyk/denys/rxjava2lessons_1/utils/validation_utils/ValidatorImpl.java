package com.kalashnyk.denys.rxjava2lessons_1.utils.validation_utils;

public class ValidatorImpl implements IValidator {

    public ValidatorImpl() {}

    @Override
    public boolean isLoginValid(CharSequence login) {
        return login.length() >= 8;
    }

    @Override
    public boolean isPasswordValid(CharSequence password) {
        return password.length() >= 8;
    }
}
