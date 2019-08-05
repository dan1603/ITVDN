package com.kalashnyk.denys.kotlinbindingsample.di.component

import com.kalashnyk.denys.kotlinbindingsample.di.module.RepositoryModule
import com.kalashnyk.denys.kotlinbindingsample.di.scope.RepositoryScope
import com.kalashnyk.denys.kotlinbindingsample.repository.AppRepository
import dagger.Component

@RepositoryScope
@Component(modules = [RepositoryModule::class], dependencies = [ApiComponent::class, DatabaseComponent::class])
interface RepositoryComponent {
    val repository: AppRepository
}