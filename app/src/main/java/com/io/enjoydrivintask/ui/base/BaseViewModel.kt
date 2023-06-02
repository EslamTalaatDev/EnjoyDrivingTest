package com.io.enjoydrivintask.ui.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

/**
 * Created by Rim Gazzah on 8/20/20.
 **/
abstract class BaseViewModel<Intent, State>(
    val intents: Channel<Intent>
) : ViewModel() {

    init {
        this.viewModelScope.launch {
            handleIntents()
        }
    }

    protected abstract suspend fun handleIntents()
    protected abstract fun viewStateReducer(sate: State): State

    override fun onCleared() {
        this.intents.cancel()
        super.onCleared()
    }


}