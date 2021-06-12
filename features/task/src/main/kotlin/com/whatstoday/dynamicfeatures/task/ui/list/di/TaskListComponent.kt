 

package com.whatstoday.dynamicfeatures.task.ui.list.di

import com.whatstoday.core.di.CoreComponent
import com.whatstoday.core.di.scopes.FeatureScope
import com.whatstoday.dynamicfeatures.task.ui.list.TaskListFragment
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [TaskListModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [TaskListModule::class],
    dependencies = [CoreComponent::class]
)
interface TaskListComponent {

    /**
     * Inject dependencies on component.
     *
     * @param listFragment Characters list component.
     */
    fun inject(listFragment: TaskListFragment)
}
