package com.kalashnyk.denys.kotlinsample

import android.app.Application
import android.arch.persistence.room.Room
import com.kalashnyk.denys.kotlinsample.di.component.*
import com.kalashnyk.denys.kotlinsample.di.module.ApiModule
import com.kalashnyk.denys.kotlinsample.di.module.DatabaseModule
import com.kalashnyk.denys.kotlinsample.di.module.RepositoryModule
import com.kalashnyk.denys.kotlinsample.di.module.ViewModelModule
import com.kalashnyk.denys.kotlinsample.repository.database.AppDatabase

class App: Application() {

    private var viewModelComponent: ViewModelComponent? = null
    private var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        initRoom()
        initDagger()
    }

    private fun initRoom() {
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    private fun initDagger() {
        val apiComponent = DaggerApiComponent.builder()
            .apiModule(ApiModule())
            .build()

        val databaseComponent = DaggerDatabaseComponent.builder()
            .databaseModule(DatabaseModule(this!!.database!!))
            .build()

        val repositoryComponent = DaggerRepositoryComponent.builder()
            .apiComponent(apiComponent)
            .databaseComponent(databaseComponent)
            .repositoryModule(RepositoryModule())
            .build()

        viewModelComponent = DaggerViewModelComponent.builder()
            .repositoryComponent(repositoryComponent)
            .viewModelModule(ViewModelModule(this))
            .build()
    }

    fun getViewModelComponent(): ViewModelComponent {
        return this!!.viewModelComponent!!
    }
}