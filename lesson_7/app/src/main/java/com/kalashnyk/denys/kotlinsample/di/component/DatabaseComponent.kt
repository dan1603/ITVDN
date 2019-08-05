package com.kalashnyk.denys.kotlinsample.di.component

import com.kalashnyk.denys.kotlinsample.di.module.DatabaseModule
import com.kalashnyk.denys.kotlinsample.repository.database.AppDatabase
import dagger.Component

@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    val database: AppDatabase
}
