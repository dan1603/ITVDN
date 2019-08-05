package com.kalashnyk.denys.kotlinbindingsample.di.module

import com.kalashnyk.denys.kotlinbindingsample.di.scope.RepositoryScope
import com.kalashnyk.denys.kotlinbindingsample.repository.AppRepository
import com.kalashnyk.denys.kotlinbindingsample.repository.database.AppDatabase
import com.kalashnyk.denys.kotlinbindingsample.repository.server.ServerCommunicator
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