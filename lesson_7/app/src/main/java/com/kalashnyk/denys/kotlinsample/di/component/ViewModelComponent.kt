package com.kalashnyk.denys.kotlinsample.di.component

import com.kalashnyk.denys.kotlinsample.di.module.ViewModelModule
import com.kalashnyk.denys.kotlinsample.di.scope.ViewModelScope
import com.kalashnyk.denys.kotlinsample.presentation.activities.detail.DetailActivity
import com.kalashnyk.denys.kotlinsample.presentation.activities.main.MainActivity
import dagger.Component

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [RepositoryComponent::class])
interface ViewModelComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
}