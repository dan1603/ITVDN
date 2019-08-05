package com.kalashnyk.denys.kotlinsample.di.module

import android.app.Application
import com.kalashnyk.denys.kotlinsample.App
import com.kalashnyk.denys.kotlinsample.di.scope.ViewModelScope
import com.kalashnyk.denys.kotlinsample.domain.AllUsersViewModel
import com.kalashnyk.denys.kotlinsample.domain.SingleUserViewModel
import com.kalashnyk.denys.kotlinsample.repository.AppRepository
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(app: App) {

    internal var app: Application = app

    @ViewModelScope
    @Provides
    internal fun providesAllUserViewModel(repository: AppRepository): AllUsersViewModel {
        return AllUsersViewModel(app, repository)
    }

    @ViewModelScope
    @Provides
    internal fun providesSingleUserViewModel(repository: AppRepository): SingleUserViewModel {
        return SingleUserViewModel(app, repository)
    }
}