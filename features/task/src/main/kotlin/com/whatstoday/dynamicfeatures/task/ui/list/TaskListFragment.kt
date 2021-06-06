package com.whatstoday.dynamicfeatures.task.ui.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import com.whatstoday.android.WhatsTodayApp.Companion.coreComponent
import com.whatstoday.commons.ui.base.BaseFragment
import com.whatstoday.commons.ui.extensions.observe
import com.whatstoday.dynamicfeatures.characterslist.R
import com.whatstoday.dynamicfeatures.characterslist.databinding.FragmentTaskListBinding
import com.whatstoday.dynamicfeatures.task.ui.list.adapter.TaskListAdapter
import com.whatstoday.dynamicfeatures.task.ui.list.adapter.TaskListAdapterState
import com.whatstoday.dynamicfeatures.task.ui.list.di.CharactersListModule
import com.whatstoday.dynamicfeatures.task.ui.list.di.DaggerCharactersListComponent
import com.whatstoday.dynamicfeatures.task.ui.list.model.CharacterItem
import javax.inject.Inject

/**
 * View listing the all marvel characters with option to display the detail view.
 *
 * @see BaseFragment
 */
class TaskListFragment :
    BaseFragment<FragmentTaskListBinding, TaskListViewModel>(
        layoutId = R.layout.fragment_task_list
    ) {

    @Inject
    lateinit var viewAdapter: TaskListAdapter

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param view The view returned by onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @see BaseFragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.state, ::onViewStateChange)
        observe(viewModel.data, ::onViewDataChange)
        observe(viewModel.event, ::onViewEvent)
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerCharactersListComponent
            .builder()
            .coreComponent(coreComponent(requireContext()))
            .charactersListModule(CharactersListModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.includeList.charactersList.apply {
            adapter = viewAdapter
            gridLayoutManager?.spanSizeLookup = viewAdapter.getSpanSizeLookup()
        }
    }

    // ============================================================================================
    //  Private observers methods
    // ============================================================================================

    /**
     * Observer view data change on [TaskListViewModel].
     *
     * @param viewData Paged list of characters.
     */
    private fun onViewDataChange(viewData: PagedList<CharacterItem>) {
        viewAdapter.submitList(viewData)
    }

    /**
     * Observer view state change on [TaskListViewModel].
     *
     * @param viewState State of characters list.
     */
    private fun onViewStateChange(viewState: TaskListViewState) {
        when (viewState) {
            is TaskListViewState.Loaded ->
                viewAdapter.submitState(TaskListAdapterState.Added)
            is TaskListViewState.AddLoading ->
                viewAdapter.submitState(TaskListAdapterState.AddLoading)
            is TaskListViewState.AddError ->
                viewAdapter.submitState(TaskListAdapterState.AddError)
            is TaskListViewState.NoMoreElements ->
                viewAdapter.submitState(TaskListAdapterState.NoMore)
        }
    }

    /**
     * Observer view event change on [TaskListViewModel].
     *
     * @param viewEvent Event on characters list.
     */
    private fun onViewEvent(viewEvent: TaskListViewEvent) {
        when (viewEvent) {
            is TaskListViewEvent.OpenCharacterDetail ->
                findNavController().navigate(
                    CharactersListFragmentDirections
                        .actionCharactersListFragmentToCharacterDetailFragment(viewEvent.id)
                )
        }
    }
}
