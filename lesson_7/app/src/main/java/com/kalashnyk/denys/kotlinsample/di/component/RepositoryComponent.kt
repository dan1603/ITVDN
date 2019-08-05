package com.kalashnyk.denys.kotlinsample.di.component

import com.kalashnyk.denys.kotlinsample.di.module.RepositoryModule
import com.kalashnyk.denys.kotlinsample.di.scope.RepositoryScope
import com.kalashnyk.denys.kotlinsample.repository.AppRepository
import dagger.Component

@RepositoryScope
@Component(modules = [RepositoryModule::class], dependencies = [ApiComponent::class, DatabaseComponent::class])
interface RepositoryComponent {
    val repository: AppRepository
}