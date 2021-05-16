package com.notes.whatstoday.base.di.component

import android.app.Application
import com.notes.whatstoday.base.di.module.BaseModule
import com.notes.whatstoday.data.DatabaseService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BaseModule::class])
interface BaseComponent {

    fun inject(app: Application)

    fun getDatabaseService(): DatabaseService

}