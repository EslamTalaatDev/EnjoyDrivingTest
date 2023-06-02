package com.io.enjoydrivintask.intents_states.splash

import com.io.enjoydrivintask.utils.ViewIntent
import com.io.enjoydrivintask.utils.ViewState
sealed class SplashIntent : ViewIntent {
    object Idle : SplashIntent()
    object CompleteSplashIntent : SplashIntent()
}
sealed class SplashState : ViewState {
    object Idle : SplashState()
    object CompleteSplashState : SplashState()

}

