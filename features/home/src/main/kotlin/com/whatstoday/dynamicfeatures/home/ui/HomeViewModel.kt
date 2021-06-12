package com.whatstoday.dynamicfeatures.home.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.whatstoday.core.database.category.CategoryRespository
import com.whatstoday.dynamicfeatures.home.R

val NAV_FRAGMENTS_ID = setOf(R.id.characters_list_fragment)

/**
 * View model responsible for preparing and managing the data for [HomeFragment].
 *
 * @see ViewModel
 */
class HomeViewModel constructor(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val categoryRepository: CategoryRespository
) : ViewModel() {

    private val _state = MutableLiveData<HomeViewState>()
    val state: LiveData<HomeViewState>
        get() = _state

    /**
     * Navigation controller add destination changed listener.
     *
     * @param navController Navigation manager.
     */

    val data = categoryRepository.getAllCategories()

    fun navigationControllerChanged(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (NAV_FRAGMENTS_ID.contains(destination.id)) {
                _state.postValue(HomeViewState.NavigationScreen)
            } else {
                _state.postValue(HomeViewState.FullScreen)
            }
        }
    }
}
