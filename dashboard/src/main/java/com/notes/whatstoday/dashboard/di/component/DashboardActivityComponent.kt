package com.notes.whatstoday.dashboard.di.component

import com.notes.whatstoday.base.di.component.BaseComponent
import com.notes.whatstoday.dashboard.di.module.DashboardModule
import com.notes.whatstoday.dashboard.di.scopes.DashboardScope
import com.notes.whatstoday.dashboard.ui.DashboardActivity
import dagger.Component

@DashboardScope
@Component(
    dependencies = [BaseComponent::class],
    modules = [DashboardModule::class]
)
interface DashboardActivityComponent {

    fun inject(activity: DashboardActivity)

}