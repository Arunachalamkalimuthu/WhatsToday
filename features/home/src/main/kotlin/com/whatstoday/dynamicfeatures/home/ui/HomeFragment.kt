package com.whatstoday.dynamicfeatures.home.ui

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import com.whatstoday.android.WhatsTodayApp
import com.whatstoday.commons.ui.base.BaseFragment
import com.whatstoday.commons.ui.extensions.horizontalLayoutManager
import com.whatstoday.commons.ui.extensions.observe
import com.whatstoday.core.database.TaskCategory
import com.whatstoday.core.utils.ThemeUtils
import com.whatstoday.dynamicfeatures.home.R
import com.whatstoday.dynamicfeatures.home.databinding.FragmentHomeBinding
import com.whatstoday.dynamicfeatures.home.databinding.NavigationHeaderBinding
import com.whatstoday.dynamicfeatures.home.ui.adapter.CateogoryListAdapter
import com.whatstoday.dynamicfeatures.home.ui.di.DaggerHomeComponent
import com.whatstoday.dynamicfeatures.home.ui.di.HomeModule
import javax.inject.Inject

private const val DELAY_TO_APPLY_THEME = 1000L

/**
 * Home principal view containing bottom navigation bar with different characters tabs.
 *
 * @see BaseFragment
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    layoutId = R.layout.fragment_home
), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var themeUtils: ThemeUtils

    @Inject
    lateinit var categoryListAdapter: CateogoryListAdapter

    private val navGraphIds = listOf(
        R.navigation.navigation_task_list_graph
    )

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
        setupToolbar()
        observe(viewModel.data, ::onViewStateChange)
    }

    /**
     * Called when all saved state has been restored into the view hierarchy of the fragment.
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state,
     * this is the state.
     * @see BaseFragment.onViewStateRestored
     */
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

    }

    private fun onViewStateChange(viewData: List<TaskCategory>) {
        categoryListAdapter.submitList(viewData)
    }
    /**
     * Initialize the contents of the Fragment host's standard options menu.
     *
     * @param menu The options menu in which you place your items.
     * @param inflater Inflater to instantiate menu XML files into Menu objects.
     * @see BaseFragment.onCreateOptionsMenu
     */


    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerHomeComponent
            .builder()
            .coreComponent(WhatsTodayApp.coreComponent(requireContext()))
            .homeModule(HomeModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.dashboardContent.categoryListView.apply {
            adapter = categoryListAdapter
            layoutManager = horizontalLayoutManager
        }
    }

    // ============================================================================================
    //  Private setups methods
    // ============================================================================================

    /**
     * Configure app custom support action bar.
     */
    private fun setupToolbar() {
        setHasOptionsMenu(true)
        requireCompatActivity().setSupportActionBar(viewBinding.dashboardContent.toolbar)
        val content = viewBinding.dashboardContent.ctRoot
        val toolbar = viewBinding.dashboardContent.toolbar
        val drawer = viewBinding.drawerLayout
        val navigationView = viewBinding.navView
        navigationView.setNavigationItemSelectedListener(this)
        val headerView: View? = viewBinding.navView.getHeaderView(0)
        val headerBinding = headerView?.let { NavigationHeaderBinding.bind(it) }
        headerBinding?.viewModel = viewModel
        if (headerBinding != null) {
            headerBinding.executePendingBindings()
        }
        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            requireCompatActivity(),
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            private val scaleFactor = 10f
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                headerBinding?.executePendingBindings()
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                val slideX: Float = drawerView.width * slideOffset
                content.translationX = slideX
                content.radius = 64f
                content.scaleX = 1 - slideOffset / scaleFactor
                content.scaleY = 1 - slideOffset / scaleFactor
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                content.radius = 0f


            }
        }
        drawer.setScrimColor(Color.TRANSPARENT)
        drawer.drawerElevation = 0f
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

    /**
     * Configure app bottom bar via navigation graph.
     */

}
