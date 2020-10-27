package com.memes.funny.data.remote.service

import com.task.data.dto.recipes.DictionaryEntity
import retrofit2.Response
import retrofit2.http.GET


interface RecipesService {
    @GET("memelist.json")
    suspend fun fetchRecipes(): Response<List<DictionaryEntity>>
}
