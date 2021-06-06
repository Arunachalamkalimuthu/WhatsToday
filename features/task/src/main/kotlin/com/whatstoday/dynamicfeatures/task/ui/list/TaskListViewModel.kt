package com.whatstoday.dynamicfeatures.task.ui.list

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import com.whatstoday.commons.ui.livedata.SingleLiveData
import com.whatstoday.core.network.NetworkState
import com.whatstoday.dynamicfeatures.task.ui.list.paging.TaskListPageDataSourceFactory
import com.whatstoday.dynamicfeatures.task.ui.list.paging.PAGE_MAX_ELEMENTS
import javax.inject.Inject

/**
 * View model responsible for preparing and managing the data for [TaskListFragment].
 *
 * @see ViewModel
 */
class TaskListViewModel
@Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val dataSourceFactory: TaskListPageDataSourceFactory
) : ViewModel() {

    @VisibleForTesting(otherwise = PRIVATE)
    val networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
        it.networkState
    }

    val event = SingleLiveData<TaskListViewEvent>()
    val data = LivePagedListBuilder(dataSourceFactory, PAGE_MAX_ELEMENTS).build()
    val state = Transformations.map(networkState) {
        when (it) {
            is NetworkState.Success ->
                if (it.isAdditional && it.isEmptyResponse) {
                    TaskListViewState.NoMoreElements
                } else if (it.isEmptyResponse) {
                    TaskListViewState.Empty
                } else {
                    TaskListViewState.Loaded
                }
            is NetworkState.Loading ->
                if (it.isAdditional) {
                    TaskListViewState.AddLoading
                } else {
                    TaskListViewState.Loading
                }
            is NetworkState.Error ->
                if (it.isAdditional) {
                    TaskListViewState.AddError
                } else {
                    TaskListViewState.Error
                }
        }
    }

    // ============================================================================================
    //  Public methods
    // ============================================================================================

    /**
     * Refresh characters fetch them again and update the list.
     */
    fun refreshLoadedCharactersList() {
        dataSourceFactory.refresh()
    }

    /**
     * Retry last fetch operation to add characters into list.
     */
    fun retryAddCharactersList() {
        dataSourceFactory.retry()
    }

    /**
     * Send interaction event for open character detail view from selected character.
     *
     * @param characterId Character identifier.
     */
    fun openCharacterDetail(characterId: Long) {
        event.postValue(TaskListViewEvent.OpenCharacterDetail(characterId))
    }
}
