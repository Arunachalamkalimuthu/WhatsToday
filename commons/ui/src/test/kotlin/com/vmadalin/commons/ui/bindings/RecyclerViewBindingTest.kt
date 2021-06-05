 

package com.whatstoday.commons.ui.bindings

import androidx.recyclerview.widget.RecyclerView
import com.whatstoday.commons.ui.recyclerview.RecyclerViewItemDecoration
import com.whatstoday.libraries.testutils.robolectric.TestRobolectric
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RecyclerViewBindingTest : TestRobolectric() {

    private lateinit var recyclerView: RecyclerView

    @Before
    fun setUp() {
        recyclerView = RecyclerView(context)
    }

    @Test
    fun setItemDecoration_ShouldAddProperlySpacing() {
        val spacingPx = 10f

        recyclerView.setItemDecorationSpacing(spacingPx)

        assertEquals(1, recyclerView.itemDecorationCount)

        val decoration = recyclerView.getItemDecorationAt(0)
        assertThat(decoration, instanceOf(RecyclerViewItemDecoration::class.java))
        assertEquals(spacingPx.toInt(), (decoration as RecyclerViewItemDecoration).spacingPx)
    }
}
