package com.whatstoday.dynamicfeatures.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import com.whatstoday.commons.ui.base.BaseListAdapter
import com.whatstoday.core.database.TaskCategory
import com.whatstoday.dynamicfeatures.home.ui.HomeViewModel
import com.whatstoday.dynamicfeatures.home.ui.adapter.viewholder.CategoryViewHolder
import javax.inject.Inject

internal enum class ItemView(val type: Int, val span: Int) {
    CATEGORY(type = 0, span = 1),
    LOADING(type = 1, span = 2),
    ERROR(type = 2, span = 2);

    companion object {
        fun valueOf(type: Int): ItemView? = values().first { it.type == type }
    }
}

class CateogoryListAdapter @Inject constructor(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val viewModel: HomeViewModel
) : BaseListAdapter<TaskCategory>(
    itemsSame = { old, new -> old.category.id == new.category.id },
    contentsSame = { old, new -> old == new }
) {

    private var state: CategoryAdapterState = CategoryAdapterState.Added

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder = when (ItemView.valueOf(viewType)) {
        ItemView.CATEGORY -> CategoryViewHolder(inflater)
        else -> throw IllegalStateException("Invalid view type")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemView(position)) {
            ItemView.CATEGORY ->
                getItem(position)?.let {
                    if (holder is CategoryViewHolder) {
                        holder.bind(viewModel, it)
                    }
                }
        }
    }

    fun submitState(newState: CategoryAdapterState) {
        val oldState = state
        state = newState
        if (newState.hasExtraRow && oldState != newState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    override fun getItemCount() =
        if (state.hasExtraRow) {
            super.getItemCount() + 1
        } else {
            super.getItemCount()
        }


    override fun getItemId(position: Int): Long = when (getItemView(position)) {
        ItemView.CATEGORY -> getItem(position)?.category?.id ?: -1L
        ItemView.LOADING -> 0L
        ItemView.ERROR -> 1L
    }

    internal fun getItemView(position: Int) =
        if (state.hasExtraRow && position == itemCount - 1) {
            if (state.isAddError()) {
                ItemView.ERROR
            } else {
                ItemView.LOADING
            }
        } else {
            ItemView.CATEGORY
        }
}
