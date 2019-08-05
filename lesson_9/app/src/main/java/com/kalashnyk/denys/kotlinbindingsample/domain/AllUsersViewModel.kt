package com.kalashnyk.denys.kotlinbindingsample.domain

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.LiveData
import com.kalashnyk.denys.kotlinbindingsample.presentation.widget.SingleLiveEvent
import com.kalashnyk.denys.kotlinbindingsample.repository.AppRepository
import com.kalashnyk.denys.kotlinbindingsample.repository.database.entity.UserEntity

class AllUsersViewModel(application: Application, private val mRepository: AppRepository) : BaseViewModel(application) {
    private val liveDataItems = SingleLiveEvent<List<UserEntity>>()

    @SuppressLint("CheckResult")
    fun getAllItems() {
        mRepository.getAll()?.subscribe { list -> liveDataItems.value = list }
    }

    fun getLiveDataItems(): LiveData<List<UserEntity>> {
        return liveDataItems
    }
}

