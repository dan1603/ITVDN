package com.kalashnyk.denys.kotlinsample.repository

import com.kalashnyk.denys.kotlinsample.repository.database.AppDatabase
import com.kalashnyk.denys.kotlinsample.repository.database.entity.UserEntity
import com.kalashnyk.denys.kotlinsample.repository.server.ServerCommunicator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class AppRepository(private val serverCommunicator: ServerCommunicator, private val mainDatabase: AppDatabase) {

    fun getAll(): Single<List<UserEntity>>? {
        return serverCommunicator.getAllUsers()
            .flatMap { list ->
                mainDatabase.userDao().insertList(Objects.requireNonNull(list.body())?.records!!)
                Single.just(mainDatabase.userDao().getAll())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getUser(id: Int): Single<UserEntity> {
        return serverCommunicator.getUser(id)
            .map {
                val user = mainDatabase.userDao().getById(id)
                user
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }
}