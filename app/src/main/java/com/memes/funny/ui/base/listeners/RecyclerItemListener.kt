package com.task.ui.base.listeners

import com.task.data.dto.recipes.DictionaryEntity


interface RecyclerItemListener {
    fun onItemSelected(recipe : DictionaryEntity)
}
