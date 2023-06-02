package com.io.enjoydrivintask.intents_states.home

import com.io.enjoydrivintask.utils.ViewIntent
import com.io.enjoydrivintask.utils.ViewState

sealed class HomeIntent : ViewIntent {
    object Idle : HomeIntent()


}

sealed class HomeState : ViewState {
    object Idle : HomeState()

}

