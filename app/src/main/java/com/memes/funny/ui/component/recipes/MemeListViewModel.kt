package com.memes.funny.ui.component.recipes

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.task.data.DataRepositorySource
import com.task.data.Resource
import com.task.data.dto.recipes.DictionaryEntity
import com.task.data.dto.recipes.Recipes
import com.task.ui.base.BaseViewModel
import com.task.utils.SingleEvent
import com.task.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class MemeListViewModel @Inject
constructor(private val dataRepositoryRepository: DataRepositorySource) : BaseViewModel() {

    /**
     * Data --> LiveData, Exposed as LiveData, Locally in viewModel as MutableLiveData
     */

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val memesLiveDataPrivate = MutableLiveData<Resource<Recipes>>()
    val memesLiveData: LiveData<Resource<Recipes>> get() = memesLiveDataPrivate


    //TODO check to make them as one Resource
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val recipeSearchFoundPrivate: MutableLiveData<DictionaryEntity> = MutableLiveData()
    val recipeSearchFound: LiveData<DictionaryEntity> get() = recipeSearchFoundPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val noSearchFoundPrivate: MutableLiveData<Unit> = MutableLiveData()
    val noSearchFound: LiveData<Unit> get() = noSearchFoundPrivate

    /**
     * UI actions as event, user action is single one time event, Shouldn't be multiple time consumption
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val openRecipeDetailsPrivate = MutableLiveData<SingleEvent<DictionaryEntity>>()
    val openRecipeDetails: LiveData<SingleEvent<DictionaryEntity>> get() = openRecipeDetailsPrivate

    /**
     * Error handling as UI
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    fun getRecipes() {
        viewModelScope.launch {
            memesLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                dataRepositoryRepository.requestMemes().collect {
                    memesLiveDataPrivate.value = it
                }
            }
        }
    }

    fun openRecipeDetails(recipe: DictionaryEntity) {
        openRecipeDetailsPrivate.value = SingleEvent(recipe)
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }

    fun onSearchClick(recipeName: String) {
        /*recipesLiveDataPrivate.value?.data?.recipesList?.let {
            if (it.isNotEmpty()) {
                for (recipe in it) {
                    if (recipe.name.toLowerCase(ROOT).contains(recipeName.toLowerCase(ROOT))) {
                        recipeSearchFoundPrivate.value = recipe
                        return
                    }
                }
            }
        }
        return noSearchFoundPrivate.postValue(Unit)*/
    }
}
