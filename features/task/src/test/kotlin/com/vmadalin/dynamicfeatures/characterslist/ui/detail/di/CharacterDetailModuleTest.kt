 

package com.whatstoday.dynamicfeatures.task.ui.detail.di

import androidx.lifecycle.ViewModel
import com.whatstoday.commons.ui.extensions.viewModel
import com.whatstoday.core.database.characterfavorite.CharacterFavoriteRepository
import com.whatstoday.dynamicfeatures.task.ui.detail.TaskDetailFragment
import com.whatstoday.dynamicfeatures.task.ui.detail.TaskDetailViewModel
import com.whatstoday.dynamicfeatures.task.ui.detail.model.TaskDetailMapper
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterDetailModuleTest {

    @MockK
    lateinit var fragment: TaskDetailFragment
    lateinit var module: CharacterDetailModule

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initializeCharacterDetailModule_ShouldSetUpCorrectly() {
        module = CharacterDetailModule(fragment)

        assertEquals(fragment, module.fragment)
    }

    @Test
    fun verifyProvidedCharacterDetailViewModel() {
        mockkStatic("com.whatstoday.commons.ui.extensions.FragmentExtensionsKt")

        every {
            fragment.viewModel(any(), any<() -> ViewModel>())
        } returns mockk<TaskDetailViewModel>()

        val factoryCaptor = slot<() -> TaskDetailViewModel>()
        val TaskRepository = mockk<TaskRepository>(relaxed = true)
        val favoriteRepository = mockk<CharacterFavoriteRepository>(relaxed = true)
        val mapper = mockk<TaskDetailMapper>(relaxed = true)
        module = CharacterDetailModule(fragment)
        module.providesCharacterDetailViewModel(
            TaskRepository = TaskRepository,
            characterFavoriteRepository = favoriteRepository,
            characterDetailMapper = mapper
        )

        verify {
            fragment.viewModel(factory = capture(factoryCaptor))
        }

        factoryCaptor.captured().run {
            assertEquals(TaskRepository, this.TaskRepository)
            assertEquals(favoriteRepository, this.characterFavoriteRepository)
            assertEquals(mapper, this.characterDetailMapper)
        }
    }
}
