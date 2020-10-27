package com.memes.funny.ui.component.recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.data.dto.recipes.DictionaryEntity
import com.memes.funny.databinding.RecipeItemBinding
import com.task.ui.base.listeners.RecyclerItemListener
import com.memes.funny.ui.component.recipes.MemeListViewModel
import com.task.ui.component.recipes.adapter.RecipeViewHolder


class MemesAdapter(private val memeListViewModel: MemeListViewModel, private val meme: List<DictionaryEntity>) : RecyclerView.Adapter<RecipeViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(recipe: DictionaryEntity) {
            memeListViewModel.openRecipeDetails(recipe)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding = RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {


        holder.bind(meme[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return meme.size
    }



}

