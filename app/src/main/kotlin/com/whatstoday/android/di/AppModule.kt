

package com.whatstoday.android.di

import android.content.Context
import com.whatstoday.android.WhatsTodayApp
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [AppComponent].
 *
 * @see Module
 */
@Module
class AppModule {

    /**
     * Create a provider method binding for [Context].
     *
     * @param application Sample Application.
     * @return Instance of context.
     * @see Provides
     */
    @Provides
    fun provideContext(application: WhatsTodayApp): Context = application.applicationContext
}
