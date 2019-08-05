package com.kalashnyk.denys.kotlinsample.domain

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.LiveData
import com.kalashnyk.denys.kotlinsample.presentation.widget.SingleLiveEvent
import com.kalashnyk.denys.kotlinsample.repository.AppRepository
import com.kalashnyk.denys.kotlinsample.repository.database.entity.UserEntity

class SingleUserViewModel(application: Application, private val mRepository: AppRepository) : BaseViewModel(application) {

    private val liveDataItem = SingleLiveEvent<UserEntity>()

    @SuppressLint("CheckResult")
    fun getItem(id: Int) {
        mRepository.getUser(id).subscribe { list -> liveDataItem.value = list }
    }

    fun getLiveDataItem(): LiveData<UserEntity> {
        return liveDataItem
    }
}