 

package com.whatstoday.dynamicfeatures.task.ui.list

/**
 * Different interaction events for [TaskListFragment].
 */
sealed class TaskListViewEvent {

    /**
     * Open character detail view.
     *
     * @param id Character identifier
     */
    data class OpenCharacterDetail(val id: Long) : TaskListViewEvent()
}
