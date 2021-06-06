 

package com.whatstoday.dynamicfeatures.task.ui.list.adapter.holders

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import com.whatstoday.dynamicfeatures.task.databinding.ListItemCharacterBinding
import com.whatstoday.dynamicfeatures.task.ui.list.TaskListViewModel
import com.whatstoday.dynamicfeatures.task.ui.list.model.CharacterItem
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharacterViewHolderTest {

    @MockK
    lateinit var view: View

    @MockK
    lateinit var layoutInflater: LayoutInflater

    @MockK(relaxed = true)
    lateinit var binding: ListItemCharacterBinding
    lateinit var viewHolder: TaskViewHolder

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun createViewHolder_ShouldInitializeCorrectly() {
        mockkStatic(ListItemCharacterBinding::class)
        every { (binding as ViewDataBinding).root } returns view
        every { ListItemCharacterBinding.inflate(layoutInflater) } returns binding

        viewHolder = TaskViewHolder(layoutInflater)

        Assert.assertEquals(binding, viewHolder.binding)
    }

    @Test
    fun bindViewHolder_ShouldBindingDataVariable() {
        mockkStatic(ListItemCharacterBinding::class)
        every { (binding as ViewDataBinding).root } returns view
        every { ListItemCharacterBinding.inflate(layoutInflater) } returns binding

        val viewModel = mockk<TaskListViewModel>()
        val characterItem = mockk<CharacterItem>()
        viewHolder = TaskViewHolder(layoutInflater)
        viewHolder.bind(viewModel, characterItem)

        verify { binding.viewModel = viewModel }
        verify { binding.character = characterItem }
        verify { binding.executePendingBindings() }
    }
}
