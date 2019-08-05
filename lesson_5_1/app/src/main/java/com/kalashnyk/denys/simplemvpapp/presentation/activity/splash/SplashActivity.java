package com.kalashnyk.denys.simplemvpapp.presentation.activity.splash;

import android.os.Handler;
import android.os.Bundle;

import com.kalashnyk.denys.simplemvpapp.R;
import com.kalashnyk.denys.simplemvpapp.di.component.PresenterComponent;
import com.kalashnyk.denys.simplemvpapp.presentation.activity.main.MainActivity;
import com.kalashnyk.denys.simplemvpapp.presentation.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    private long SPLASH_TIMER = 3000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> {
            startActivity(MainActivity.newInstance(this));
            finish();
        }, SPLASH_TIMER);
    }

    @Override
    protected void injectDependency(PresenterComponent presentersComponent) {

    }
}
