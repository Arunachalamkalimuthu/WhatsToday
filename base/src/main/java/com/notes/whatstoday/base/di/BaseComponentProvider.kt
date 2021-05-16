package com.notes.whatstoday.base.di

import com.notes.whatstoday.base.di.component.BaseComponent

interface BaseComponentProvider {

    fun provideBaseComponent(): BaseComponent

}