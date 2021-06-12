package com.whatstoday.dynamicfeatures.home.ui.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.whatstoday.commons.ui.extensions.viewModel
import com.whatstoday.core.database.category.CategoryRespository
import com.whatstoday.core.di.scopes.FeatureScope
import com.whatstoday.dynamicfeatures.home.ui.HomeFragment
import com.whatstoday.dynamicfeatures.home.ui.HomeViewModel
import com.whatstoday.dynamicfeatures.home.ui.adapter.CateogoryListAdapter
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [HomeComponent].
 *
 * @see Module
 */
@Module
class HomeModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: HomeFragment
) {

    /**
     * Create a provider method binding for [HomeViewModel].
     *
     * @return Instance of view model.
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun providesHomeViewModel(
        categoryRespository: CategoryRespository
    ) = fragment.viewModel {
        HomeViewModel(categoryRespository)
    }


    @FeatureScope
    @Provides
    fun providesHomeCategoryAdapter(
        viewModel: HomeViewModel
    ) = CateogoryListAdapter(viewModel)
}
