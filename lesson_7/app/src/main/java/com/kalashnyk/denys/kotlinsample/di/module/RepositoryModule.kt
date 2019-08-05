package com.kalashnyk.denys.kotlinsample.di.module

import com.kalashnyk.denys.kotlinsample.di.scope.RepositoryScope
import com.kalashnyk.denys.kotlinsample.repository.AppRepository
import com.kalashnyk.denys.kotlinsample.repository.database.AppDatabase
import com.kalashnyk.denys.kotlinsample.repository.server.ServerCommunicator
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @RepositoryScope
    @Provides
    internal fun providesRepository(communicator: ServerCommunicator, database: AppDatabase): AppRepository {
        return AppRepository(communicator, database)
    }
}