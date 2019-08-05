package com.kalashnyk.denys.kotlinbindingsample.di.component

import com.kalashnyk.denys.kotlinbindingsample.di.module.DatabaseModule
import com.kalashnyk.denys.kotlinbindingsample.repository.database.AppDatabase
import dagger.Component

@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    val database: AppDatabase
}
