package com.kalashnyk.denys.kotlinbindingsample.di.module

import android.app.Application
import com.kalashnyk.denys.kotlinbindingsample.App
import com.kalashnyk.denys.kotlinbindingsample.di.scope.ViewModelScope
import com.kalashnyk.denys.kotlinbindingsample.domain.AllUsersViewModel
import com.kalashnyk.denys.kotlinbindingsample.domain.SingleUserViewModel
import com.kalashnyk.denys.kotlinbindingsample.repository.AppRepository
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