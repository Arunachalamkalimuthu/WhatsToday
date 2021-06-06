 

package com.whatstoday.dynamicfeatures.task.ui.list

import org.junit.Assert.assertEquals
import org.junit.Test

class CharactersListViewEventTest {

    lateinit var event: TaskListViewEvent

    @Test
    fun setEventOpenCharacterDetail_ShouldSettledCorrectly() {
        val characterId = 1L
        event = TaskListViewEvent.OpenCharacterDetail(characterId)

        val eventOpenDetail = event as TaskListViewEvent.OpenCharacterDetail
        assertEquals(characterId, eventOpenDetail.id)
    }
}
