package com.notes.whatstoday.base

import android.content.Context
import com.notes.whatstoday.base.di.BaseComponentProvider
import com.notes.whatstoday.base.di.component.BaseComponent

object InjectUtils {

    fun provideBaseComponent(applicationContext: Context): BaseComponent {
        return if (applicationContext is BaseComponentProvider) {
            (applicationContext as BaseComponentProvider).provideBaseComponent()
        } else {
            throw IllegalStateException("Provide the application context which implement BaseComponentProvider")
        }
    }

}