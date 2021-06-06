 

package com.whatstoday.dynamicfeatures.task.ui.list.adapter

import org.junit.Assert
import org.junit.Test

class CharactersListAdapterStateTest {

    lateinit var state: TaskListAdapterState

    @Test
    fun setStateAsAdded_ShouldBeSettled() {
        state = TaskListAdapterState.Added

        Assert.assertTrue(state.hasExtraRow)
        Assert.assertTrue(state.isAdded())

        Assert.assertFalse(state.isAddLoading())
        Assert.assertFalse(state.isAddError())
        Assert.assertFalse(state.isNoMore())
    }

    @Test
    fun setStateAsAddLoading_ShouldBeSettled() {
        state = TaskListAdapterState.AddLoading

        Assert.assertTrue(state.hasExtraRow)
        Assert.assertTrue(state.isAddLoading())

        Assert.assertFalse(state.isAdded())
        Assert.assertFalse(state.isAddError())
        Assert.assertFalse(state.isNoMore())
    }

    @Test
    fun setStateAsAddError_ShouldBeSettled() {
        state = TaskListAdapterState.AddError

        Assert.assertTrue(state.hasExtraRow)
        Assert.assertTrue(state.isAddError())

        Assert.assertFalse(state.isAdded())
        Assert.assertFalse(state.isAddLoading())
        Assert.assertFalse(state.isNoMore())
    }

    @Test
    fun setStateAsNoMore_ShouldBeSettled() {
        state = TaskListAdapterState.NoMore

        Assert.assertFalse(state.hasExtraRow)
        Assert.assertTrue(state.isNoMore())

        Assert.assertFalse(state.isAdded())
        Assert.assertFalse(state.isAddLoading())
        Assert.assertFalse(state.isAddError())
    }
}
