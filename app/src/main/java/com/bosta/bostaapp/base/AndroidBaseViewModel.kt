package com.bosta.bostaapp.base

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class AndroidBaseViewModel<T : Action>(val app: Application) : AndroidViewModel(app) {
    private val nextAction = MutableSharedFlow<T>()
    val viewState: SharedFlow<T> = nextAction

    // use this method to produce an action to the view
    fun produce(t: T) {
        viewModelScope.launch {
            nextAction.emit(t)
        }
    }

    fun getString(@StringRes stringRes: Int): String {
        return app.getString(stringRes)
    }
}