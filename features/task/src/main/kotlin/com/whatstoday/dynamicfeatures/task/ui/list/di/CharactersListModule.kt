 

package com.whatstoday.dynamicfeatures.task.ui.list.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.viewModelScope
import com.whatstoday.commons.ui.extensions.viewModel
import com.whatstoday.core.di.scopes.FeatureScope
import com.whatstoday.core.network.repositiories.MarvelRepository
import com.whatstoday.dynamicfeatures.task.ui.list.TaskListFragment
import com.whatstoday.dynamicfeatures.task.ui.list.TaskListViewModel
import com.whatstoday.dynamicfeatures.task.ui.list.adapter.TaskListAdapter
import com.whatstoday.dynamicfeatures.task.ui.list.model.CharacterItemMapper
import com.whatstoday.dynamicfeatures.task.ui.list.paging.TaskListPageDataSource
import com.whatstoday.dynamicfeatures.task.ui.list.paging.TaskListPageDataSourceFactory
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [CharactersListComponent].
 *
 * @see Module
 */
@Module
class CharactersListModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: TaskListFragment
) {

    /**
     * Create a provider method binding for [TaskListViewModel].
     *
     * @param dataFactory Data source factory for characters.
     * @return Instance of view model.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesCharactersListViewModel(
        dataFactory: TaskListPageDataSourceFactory
    ) = fragment.viewModel {
        TaskListViewModel(dataFactory)
    }

    /**
     * Create a provider method binding for [TaskListPageDataSource].
     *
     * @return Instance of data source.
     * @see Provides
     */
    @Provides
    fun providesCharactersPageDataSource(
        viewModel: TaskListViewModel,
        repository: MarvelRepository,
        mapper: CharacterItemMapper
    ) = TaskListPageDataSource(
        repository = repository,
        scope = viewModel.viewModelScope,
        mapper = mapper
    )

    /**
     * Create a provider method binding for [CharacterItemMapper].
     *
     * @return Instance of mapper.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesCharacterItemMapper() = CharacterItemMapper()

    /**
     * Create a provider method binding for [TaskListAdapter].
     *
     * @return Instance of adapter.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesCharactersListAdapter(
        viewModel: TaskListViewModel
    ) = TaskListAdapter(viewModel)
}
