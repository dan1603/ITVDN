package com.kalashnyk.denys.gmapsapp.presentation.activities.splash;

import android.os.Handler;
import android.os.Bundle;

import com.kalashnyk.denys.gmapsapp.R;
import com.kalashnyk.denys.gmapsapp.di.component.ViewModelComponent;
import com.kalashnyk.denys.gmapsapp.presentation.activities.main.MainActivity;
import com.kalashnyk.denys.gmapsapp.presentation.base.BaseActivity;

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
    protected void injectDependency(ViewModelComponent component) { }
}
