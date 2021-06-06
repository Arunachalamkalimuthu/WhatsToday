

package com.whatstoday.dynamicfeatures.task.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.whatstoday.core.network.NetworkState
import com.whatstoday.dynamicfeatures.task.ui.list.paging.TaskListPageDataSource
import com.whatstoday.dynamicfeatures.task.ui.list.paging.TaskListPageDataSourceFactory
import com.whatstoday.libraries.testutils.rules.CoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharactersListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var dataSourceFactory: TaskListPageDataSourceFactory
    @MockK(relaxed = true)
    lateinit var stateObserver: Observer<TaskListViewState>
    @MockK(relaxed = true)
    lateinit var eventObserver: Observer<TaskListViewEvent>
    lateinit var viewModel: TaskListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun networkSuccessEmptyCharacters_ShouldBeEmptyState() {
        val networkState = NetworkState.Success(
            isEmptyResponse = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<TaskListPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = TaskListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = TaskListViewState.Empty
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkSuccessAdditionalEmptyCharacters_ShouldBeNoMoreElementsState() {
        val networkState = NetworkState.Success(
            isAdditional = true,
            isEmptyResponse = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<TaskListPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = TaskListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = TaskListViewState.NoMoreElements
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkSuccessAdditionalCharacters_ShouldBeLoadedState() {
        val networkState = NetworkState.Success(
            isAdditional = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<TaskListPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = TaskListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = TaskListViewState.Loaded
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkSuccessCharacters_ShouldBeLoadedState() {
        val networkState = NetworkState.Success()
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<TaskListPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = TaskListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = TaskListViewState.Loaded
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkLoadingCharacters_ShouldBeLoadingState() {
        val networkState = NetworkState.Loading()
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<TaskListPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = TaskListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = TaskListViewState.Loading
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkAdditionalLoadingCharacters_ShouldBeAddLoadingState() {
        val networkState = NetworkState.Loading(
            isAdditional = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<TaskListPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = TaskListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = TaskListViewState.AddLoading
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkErrorCharacters_ShouldBeErrorState() {
        val networkState = NetworkState.Error()
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<TaskListPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = TaskListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = TaskListViewState.Error
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkAdditionalErrorCharacters_ShouldBeAddErrorState() {
        val networkState = NetworkState.Error(true)
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<TaskListPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = TaskListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = TaskListViewState.AddError
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun refreshCharacterList_ShouldInvokeDataSourceMethod() {
        viewModel = TaskListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.refreshLoadedCharactersList()

        verify { dataSourceFactory.refresh() }
        verify(exactly = 0) { dataSourceFactory.retry() }
    }

    @Test
    fun retryCharacterList_ShouldInvokeDataSourceMethod() {
        viewModel = TaskListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.retryAddCharactersList()

        verify { dataSourceFactory.retry() }
        verify(exactly = 0) { dataSourceFactory.refresh() }
    }

    @Test
    fun openCharacterDetail_ShouldSendAsEvent() {
        viewModel = TaskListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.event.observeForever(eventObserver)

        val characterId = 1L
        viewModel.openCharacterDetail(characterId)

        val expectedEvent = TaskListViewEvent.OpenCharacterDetail(characterId)
        assertEquals(expectedEvent, viewModel.event.value)
        verify { eventObserver.onChanged(expectedEvent) }
    }

    inner class FakeCharactersPageDataSource(
        forceNetworkState: NetworkState
    ) : TaskListPageDataSource(
        repository = mockk(),
        scope = mockk(),
        mapper = mockk()
    ) {
        init {
            networkState.postValue(forceNetworkState)
        }
    }
}
