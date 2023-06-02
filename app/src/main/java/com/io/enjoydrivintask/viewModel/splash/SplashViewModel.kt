package com.io.enjoydrivintask.viewModel.splash

import com.io.enjoydrivintask.intents_states.splash.SplashIntent
import com.io.enjoydrivintask.intents_states.splash.SplashState
import com.io.enjoydrivintask.ui.base.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow

class SplashViewModel(
    intents: Channel<SplashIntent> = Channel(Channel.CONFLATED),
    state: MutableStateFlow<SplashState> = MutableStateFlow(SplashState.Idle)

) :
    BaseViewModel<SplashIntent, SplashState>(intents) {
    private val _viewState: MutableStateFlow<SplashState> = state

    val viewState: StateFlow<SplashState>
        get() = this._viewState

    override suspend fun handleIntents() {
        this.intents.consumeAsFlow().collect {
            when (it) {
                is SplashIntent.Idle -> {
                    _viewState.value =
                        this.viewStateReducer(SplashState.Idle)
                }
                is SplashIntent.CompleteSplashIntent -> {
                    _viewState.value =
                        this.viewStateReducer(completeSplash())
                }
            }
        }

    }

    override fun viewStateReducer(stateData: SplashState): SplashState {
        return when (stateData) {
            SplashState.Idle,
            SplashState.CompleteSplashState -> {
                stateData
            }

        }
    }

    private fun completeSplash(): SplashState = SplashState.CompleteSplashState

}