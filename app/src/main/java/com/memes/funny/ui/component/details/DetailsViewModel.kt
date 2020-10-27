package com.task.ui.component.details

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.DataRepositorySource
import com.task.data.Resource
import com.task.data.dto.recipes.DictionaryEntity
import com.task.ui.base.BaseViewModel
import javax.inject.Inject



open class DetailsViewModel @Inject constructor(private val dataRepository: DataRepositorySource) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val recipePrivate = MutableLiveData<DictionaryEntity>()
    val recipeData: LiveData<DictionaryEntity> get() = recipePrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val isFavouritePrivate = MutableLiveData<Resource<Boolean>>()
    val isFavourite: LiveData<Resource<Boolean>> get() = isFavouritePrivate

    fun initIntentData(recipe: DictionaryEntity) {
        recipePrivate.value = recipe
    }

  /*  open fun addToFavourites() {
        viewModelScope.launch {
            isFavouritePrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                recipePrivate.value?.id?.let {
                    dataRepository.addToFavourite(it).collect { isAdded ->
                        isFavouritePrivate.value = isAdded
                    }
                }
            }
        }
    }*/

  /*  fun removeFromFavourites() {
        viewModelScope.launch {
            isFavouritePrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                recipePrivate.value?.id?.let {
                    dataRepository.removeFromFavourite(it).collect { isRemoved ->
                        when (isRemoved) {
                            is Resource.Success -> {
                                isRemoved.data?.let { isFavouritePrivate.value = Resource.Success(!isRemoved.data) }
                            }
                            is Resource.DataError -> {
                                isFavouritePrivate.value = isRemoved
                            }
                            is Resource.Loading -> {
                                isFavouritePrivate.value = isRemoved
                            }
                        }
                    }
                }
            }
        }
    }*/

 /*   fun isFavourites() {
        viewModelScope.launch {
            isFavouritePrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                recipePrivate.value?.id?.let {
                    dataRepository.isFavourite(it).collect { isFavourites ->
                        isFavouritePrivate.value = isFavourites
                    }
                }
            }
        }
    }*/
}
