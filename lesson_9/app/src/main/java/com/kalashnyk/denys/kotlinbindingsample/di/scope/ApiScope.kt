package com.kalashnyk.denys.kotlinbindingsample.di.scope

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

@Scope
@Retention(RetentionPolicy.CLASS)
annotation class ApiScope