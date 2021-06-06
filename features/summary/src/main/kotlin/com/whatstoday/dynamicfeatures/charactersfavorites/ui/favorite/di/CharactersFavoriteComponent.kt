 

package com.whatstoday.dynamicfeatures.charactersfavorites.ui.favorite.di

import com.whatstoday.core.di.CoreComponent
import com.whatstoday.core.di.scopes.FeatureScope
import com.whatstoday.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteFragment
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [CharactersFavoriteModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [CharactersFavoriteModule::class],
    dependencies = [CoreComponent::class]
)
interface CharactersFavoriteComponent {

    /**
     * Inject dependencies on component.
     *
     * @param favoriteFragment Favorite component.
     */
    fun inject(favoriteFragment: CharactersFavoriteFragment)
}
