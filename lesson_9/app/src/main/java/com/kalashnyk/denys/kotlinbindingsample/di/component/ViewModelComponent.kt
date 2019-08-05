package com.kalashnyk.denys.kotlinbindingsample.di.component

import com.kalashnyk.denys.kotlinbindingsample.di.module.ViewModelModule
import com.kalashnyk.denys.kotlinbindingsample.di.scope.ViewModelScope
import com.kalashnyk.denys.kotlinbindingsample.presentation.activities.detail.DetailActivity
import com.kalashnyk.denys.kotlinbindingsample.presentation.activities.main.MainActivity
import dagger.Component

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [RepositoryComponent::class])
interface ViewModelComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
}