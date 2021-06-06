

package com.whatstoday.dynamicfeatures.task.ui.detail.di

import com.whatstoday.core.di.CoreComponent
import com.whatstoday.core.di.scopes.FeatureScope
import com.whatstoday.dynamicfeatures.task.ui.detail.TaskDetailFragment
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [CharacterDetailModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [CharacterDetailModule::class],
    dependencies = [CoreComponent::class]
)
interface CharacterDetailComponent {

    /**
     * Inject dependencies on component.
     *
     * @param detailFragment Detail component.
     */
    fun inject(detailFragment: TaskDetailFragment)
}
