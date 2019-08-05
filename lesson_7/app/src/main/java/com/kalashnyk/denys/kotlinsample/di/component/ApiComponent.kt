package com.kalashnyk.denys.kotlinsample.di.component

import com.kalashnyk.denys.kotlinsample.di.module.ApiModule
import com.kalashnyk.denys.kotlinsample.di.scope.ApiScope
import com.kalashnyk.denys.kotlinsample.repository.server.ServerCommunicator
import dagger.Component

@ApiScope
@Component(modules = [ApiModule::class])
interface ApiComponent {
    val serverCommunicator: ServerCommunicator
}
