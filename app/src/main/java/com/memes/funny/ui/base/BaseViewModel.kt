package com.task.ui.base

import androidx.lifecycle.ViewModel
import com.memes.funny.data.error.mapper.ErrorMapper
import com.task.usecase.errors.ErrorManager





abstract class BaseViewModel : ViewModel() {
    /**Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    val errorManager: ErrorManager = ErrorManager(ErrorMapper())
}
