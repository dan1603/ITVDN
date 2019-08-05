package com.kalashnyk.denys.retrofitexample.presentation.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kalashnyk.denys.retrofitexample.R;
import com.kalashnyk.denys.retrofitexample.presentation.adapter.TabPagerAdapter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPages();
    }

    private void initPages() {
        TabPagerAdapter fragmentAdapter = new TabPagerAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.viewpager_main);
        TabLayout layout = findViewById(R.id.tabs_main);
        pager.setAdapter(fragmentAdapter);
        layout.setupWithViewPager(pager);
        getSupportActionBar().setElevation(0f);
    }
}
