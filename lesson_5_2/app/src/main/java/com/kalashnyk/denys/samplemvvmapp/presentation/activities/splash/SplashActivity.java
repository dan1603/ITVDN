package com.kalashnyk.denys.samplemvvmapp.presentation.activities.splash;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kalashnyk.denys.samplemvvmapp.R;
import com.kalashnyk.denys.samplemvvmapp.di.component.ViewModelComponent;
import com.kalashnyk.denys.samplemvvmapp.presentation.activities.main.MainActivity;
import com.kalashnyk.denys.samplemvvmapp.presentation.base.BaseActivity;

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
    protected void injectDependency(ViewModelComponent component) {

    }
}
