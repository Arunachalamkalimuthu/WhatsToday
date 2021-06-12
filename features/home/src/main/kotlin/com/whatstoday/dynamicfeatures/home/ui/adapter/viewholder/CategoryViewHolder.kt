package com.whatstoday.dynamicfeatures.home.ui.adapter.viewholder

import android.view.LayoutInflater
import com.whatstoday.commons.ui.base.BaseViewHolder
import com.whatstoday.core.database.TaskCategory
import com.whatstoday.core.database.category.Category
import com.whatstoday.dynamicfeatures.home.databinding.ContainerCategoryBinding
import com.whatstoday.dynamicfeatures.home.ui.HomeViewModel

class CategoryViewHolder(inflater: LayoutInflater) :
    BaseViewHolder<ContainerCategoryBinding>(binding = ContainerCategoryBinding.inflate(inflater)) {


    fun bind(viewModel: HomeViewModel, item: TaskCategory) {
        binding.viewModel = viewModel
        binding.category = item
        binding.executePendingBindings()
    }

}
