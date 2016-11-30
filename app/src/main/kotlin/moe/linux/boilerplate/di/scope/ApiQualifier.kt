package moe.linux.boilerplate.di.scope

import javax.inject.Qualifier

@Qualifier
@Retention
annotation class ApiQualifier(val value: String)
