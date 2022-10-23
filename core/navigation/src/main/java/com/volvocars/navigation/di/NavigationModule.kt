package com.volvocars.navigation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.volvocars.navigation.INavigationManager
import com.volvocars.navigation.NavigationManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Singleton
    @Binds
    abstract fun navigationManager(navigator: NavigationManager): INavigationManager
}
