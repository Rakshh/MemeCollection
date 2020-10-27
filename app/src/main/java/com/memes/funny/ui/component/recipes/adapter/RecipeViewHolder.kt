package com.task.ui.component.recipes.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.memes.funny.R
import com.task.data.dto.recipes.DictionaryEntity
import com.memes.funny.databinding.RecipeItemBinding
import com.task.ui.base.listeners.RecyclerItemListener


class RecipeViewHolder(private val itemBinding: RecipeItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(recipesItem: DictionaryEntity, recyclerItemListener: RecyclerItemListener) {
        //itemBinding.tvCaption.text = recipesItem.egrammar
       // itemBinding.tvName.text = recipesItem.eword
        Picasso.get().load(recipesItem.egrammar).placeholder(R.drawable.logo).error(R.drawable.logo).into(itemBinding.ivRecipeItemImage)
        itemBinding.rlRecipeItem.setOnClickListener { recyclerItemListener.onItemSelected(recipesItem) }

    }
}

