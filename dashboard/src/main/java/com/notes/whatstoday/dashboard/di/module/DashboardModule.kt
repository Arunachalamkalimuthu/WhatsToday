package com.notes.whatstoday.dashboard.di.module

import com.notes.whatstoday.dashboard.repository.DashboardRespository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DashboardModule {

    @Provides
    @Singleton
    fun provideRepository() = DashboardRespository()

}

