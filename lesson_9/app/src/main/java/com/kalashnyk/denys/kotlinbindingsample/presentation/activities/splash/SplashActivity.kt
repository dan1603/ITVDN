package com.kalashnyk.denys.kotlinbindingsample.presentation.activities.splash

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.kalashnyk.denys.kotlinbindingsample.R
import com.kalashnyk.denys.kotlinbindingsample.databinding.ActivitySplashBinding
import com.kalashnyk.denys.kotlinbindingsample.presentation.activities.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private val timer = 3000L
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(MainActivity.newInstance(this))
            finish()
        }, timer)
    }
}
