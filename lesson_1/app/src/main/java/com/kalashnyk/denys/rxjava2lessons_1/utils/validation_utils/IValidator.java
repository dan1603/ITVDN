package com.kalashnyk.denys.rxjava2lessons_1.utils.validation_utils;

public interface IValidator {

    boolean isLoginValid(CharSequence login);
    boolean isPasswordValid(CharSequence password);
}
