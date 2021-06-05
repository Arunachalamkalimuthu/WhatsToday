

package com.whatstoday.android.di

import com.whatstoday.android.WhatsTodayApp
import com.whatstoday.core.di.CoreComponent
import com.whatstoday.core.di.scopes.AppScope
import dagger.Component

/**
 * App component that application component's components depend on.
 *
 * @see Component
 */
@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {

    /**
     * Inject dependencies on application.
     *
     * @param application The sample application.
     */
    fun inject(application: WhatsTodayApp)
}
