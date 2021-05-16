package com.notes.whatstoday

import android.app.Application
import com.notes.whatstoday.base.di.BaseComponentProvider
import com.notes.whatstoday.base.di.component.BaseComponent
import com.notes.whatstoday.base.di.component.DaggerBaseComponent
import com.notes.whatstoday.base.di.module.BaseModule


class WhatsToday : Application(), BaseComponentProvider {

    lateinit var baseComponent: BaseComponent

    override fun onCreate() {
        super.onCreate()

        baseComponent = DaggerBaseComponent
            .builder()
            .baseModule(BaseModule())
            .build()
        baseComponent.inject(this)
    }

    override fun provideBaseComponent(): BaseComponent {
        return baseComponent
    }

}