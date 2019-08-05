package com.kalashnyk.denys.simplemvpapp.utils.local_helper;

import android.content.Context;

import java.util.Locale;

public interface ILocaleHelper {
    Locale getActualLocale(Context context);
    String getActualLanguage(Context context);
    Context updateLocale(Context context, ILocaleHelper.TypeLocale typeLocale);
    Context setLocale(Context context);

    enum TypeLocale {
        LOCALE_EN,
        LOCALE_RU
    }
}
