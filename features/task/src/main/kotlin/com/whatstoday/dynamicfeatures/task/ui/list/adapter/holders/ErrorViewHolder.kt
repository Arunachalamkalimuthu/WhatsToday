

package com.whatstoday.dynamicfeatures.task.ui.list.adapter.holders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.whatstoday.commons.ui.base.BaseViewHolder
import com.whatstoday.dynamicfeatures.task.databinding.ListItemErrorBinding
import com.whatstoday.dynamicfeatures.task.ui.list.TaskListViewModel

/**
 * Class describes characters error view and metadata about its place within the [RecyclerView].
 *
 * @see BaseViewHolder
 */
class ErrorViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemErrorBinding>(
    binding = ListItemErrorBinding.inflate(inflater)
) {

    /**
     * Bind view data binding variables.
     *
     * @param viewModel character list view model.
     */
    fun bind(viewModel: TaskListViewModel) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}
