 

package com.whatstoday.dynamicfeatures.task.ui.list.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whatstoday.commons.ui.extensions.viewModel
import com.whatstoday.dynamicfeatures.task.ui.list.TaskListFragment
import com.whatstoday.dynamicfeatures.task.ui.list.TaskListViewModel
import com.whatstoday.dynamicfeatures.task.ui.list.model.CharacterItemMapper
import com.whatstoday.dynamicfeatures.task.ui.list.paging.TaskListPageDataSourceFactory
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharactersListModuleTest {

    @MockK
    lateinit var fragment: TaskListFragment
    lateinit var module: TaskListModule

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initializeCharactersListModule_ShouldSetUpCorrectly() {
        module = TaskListModule(fragment)

        assertEquals(fragment, module.fragment)
    }

    @Test
    fun verifyProvidedCharactersListViewModel() {
        mockkStatic("com.whatstoday.commons.ui.extensions.FragmentExtensionsKt")

        every {
            fragment.viewModel(any(), any<() -> ViewModel>())
        } returns mockk<TaskListViewModel>()

        val factoryCaptor = slot<() -> TaskListViewModel>()
        val dataFactory = mockk<TaskListPageDataSourceFactory>(relaxed = true)
        module = TaskListModule(fragment)
        module.providesCharactersListViewModel(dataFactory)

        verify {
            fragment.viewModel(factory = capture(factoryCaptor))
        }

        assertEquals(dataFactory, factoryCaptor.captured().dataSourceFactory)
    }

    @Test
    fun verifyProvidedCharactersPageDataSource() {
        val repository = mockk<TaskRepository>(relaxed = true)
        val mapper = mockk<CharacterItemMapper>(relaxed = true)
        val viewModel = mockk<TaskListViewModel>(relaxed = true)
        val scope = mockk<CoroutineScope>()
        every { viewModel.viewModelScope } returns scope

        module = TaskListModule(fragment)
        val dataSource = module.providesCharactersPageDataSource(
            viewModel = viewModel,
            repository = repository,
            mapper = mapper
        )

        assertEquals(repository, dataSource.repository)
        assertEquals(mapper, dataSource.mapper)
        assertEquals(scope, dataSource.scope)
    }
}
