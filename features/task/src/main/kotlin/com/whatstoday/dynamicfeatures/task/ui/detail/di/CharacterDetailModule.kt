 

package com.whatstoday.dynamicfeatures.task.ui.detail.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.whatstoday.commons.ui.extensions.viewModel
import com.whatstoday.commons.views.ProgressBarDialog
import com.whatstoday.core.database.characterfavorite.CharacterFavoriteRepository
import com.whatstoday.core.di.scopes.FeatureScope
import com.whatstoday.core.network.repositiories.MarvelRepository
import com.whatstoday.dynamicfeatures.task.ui.detail.TaskDetailFragment
import com.whatstoday.dynamicfeatures.task.ui.detail.CharacterDetailViewModel
import com.whatstoday.dynamicfeatures.task.ui.detail.model.CharacterDetailMapper
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [CharacterDetailComponent].
 *
 * @see Module
 */
@Module
class CharacterDetailModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: TaskDetailFragment
) {

    /**
     * Create a provider method binding for [CharacterDetailViewModel].
     *
     * @param marvelRepository
     * @param characterFavoriteRepository handler character favorite repository
     * @param characterDetailMapper mapper to parse view model
     *
     * @return instance of view model.
     */
    @FeatureScope
    @Provides
    fun providesCharacterDetailViewModel(
        marvelRepository: MarvelRepository,
        characterFavoriteRepository: CharacterFavoriteRepository,
        characterDetailMapper: CharacterDetailMapper
    ) = fragment.viewModel {
        CharacterDetailViewModel(
            marvelRepository = marvelRepository,
            characterFavoriteRepository = characterFavoriteRepository,
            characterDetailMapper = characterDetailMapper
        )
    }

    /**
     * Create a provider method binding for [CharacterDetailMapper].
     *
     * @return instance of mapper.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesCharacterDetailMapper() = CharacterDetailMapper()

    /**
     * Create a provider method binding for [ProgressBarDialog].
     *
     * @return instance of dialog.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesProgressBarDialog() = ProgressBarDialog(fragment.requireContext())
}
