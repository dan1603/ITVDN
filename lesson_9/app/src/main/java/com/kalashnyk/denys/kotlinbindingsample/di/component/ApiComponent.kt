package com.kalashnyk.denys.kotlinbindingsample.di.component

import com.kalashnyk.denys.kotlinbindingsample.di.module.ApiModule
import com.kalashnyk.denys.kotlinbindingsample.di.scope.ApiScope
import com.kalashnyk.denys.kotlinbindingsample.repository.server.ServerCommunicator
import dagger.Component

@ApiScope
@Component(modules = [ApiModule::class])
interface ApiComponent {
    val serverCommunicator: ServerCommunicator
}
