package com.kalashnyk.denys.simplemvpapp.utils.local_helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import com.kalashnyk.denys.simplemvpapp.R;

import java.util.Locale;

public class LocaleHelperImpl implements ILocaleHelper {

    public static final Locale US = new Locale("en");
    public static final Locale RU = new Locale("ru");

    private static Locale mActualLocale = US;
    private static String mActualLanguage = "English";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @SuppressLint("CommitPrefEdits")
    public LocaleHelperImpl() {
    }

    public Context setLocale(Context context) {
        return setNewLocale(context);
    }

    @Override
    public Locale getActualLocale(Context context) {
        mSharedPreferences = context.getSharedPreferences("LOCALE", Context.MODE_PRIVATE);
        return new Locale(mSharedPreferences.getString("Locale", "en"));
    }

    @Override
    public String getActualLanguage(Context context) {
        mSharedPreferences = context.getSharedPreferences("LOCALE", Context.MODE_PRIVATE);
        return mSharedPreferences.getString("Language", "English");
    }

    @Override
    public Context updateLocale(Context context, ILocaleHelper.TypeLocale typeLocale) {
        mSharedPreferences = context.getSharedPreferences("LOCALE", Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        switch (typeLocale) {
            case LOCALE_EN:
                mActualLanguage = context.getResources().getString(R.string.english);
                mActualLocale = US;
                mEditor.putString("Locale", mActualLocale.toLanguageTag());
                mEditor.putString("Language", mActualLanguage);
                mEditor.commit();
                getSwitchLanguage(context, mActualLocale);
                Log.d("LocaleChack", "LocaleHelperImpl\nupdateLocale\nLOCALE_EN\nmActualLocale.toLanguageTag()\n" + mActualLocale.toLanguageTag() + "\nmActualLanguage\n" + mActualLanguage);
                break;
            case LOCALE_RU:
                mActualLanguage = context.getResources().getString(R.string.russian);
                mActualLocale = RU;
                mEditor.putString("Locale", mActualLocale.toLanguageTag());
                mEditor.putString("Language", mActualLanguage);
                mEditor.commit();
                getSwitchLanguage(context, mActualLocale);
                Log.d("LocaleChack", "LocaleHelperImpl\nupdateLocale\nLOCALE_RU\nmActualLocale.toLanguageTag()\n" + mActualLocale.getLanguage() + "\nmActualLanguage\n" + mActualLanguage);
                break;
        }
        return context;
    }

    private Context setNewLocale(Context context) {
        return updateLocale(context, Locale.getDefault() == LocaleHelperImpl.US ?
                ILocaleHelper.TypeLocale.LOCALE_EN :
                ILocaleHelper.TypeLocale.LOCALE_RU);
    }

    private void getSwitchLanguage(Context context, Locale locale) {
        Log.d("LocaleChack", "LocaleHelperImpl\ngetSwitchLanguage\nlocale\n" + locale.getLanguage() + "\nlocale.toLanguageTag()\n" + locale.toLanguageTag());
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(locale);
        res.updateConfiguration(conf, dm);
    }
}
