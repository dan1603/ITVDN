package com.kalashnyk.denys.kotlinsample.domain

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.support.annotation.StringRes
import com.kalashnyk.denys.kotlinsample.App

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    fun getContext() = getApplication<App>()
    fun getString(@StringRes id: Int): String = getContext().getString(id)
}