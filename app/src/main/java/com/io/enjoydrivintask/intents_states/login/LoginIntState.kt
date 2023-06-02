package com.io.enjoydrivintask.intents_states.login

import androidx.annotation.StringRes
import com.io.enjoydrivintask.utils.ViewIntent
import com.io.enjoydrivintask.utils.ViewState

sealed class LoginIntent : ViewIntent {
    object Idle : LoginIntent()
    data class ValidateData(var userName: String, var password: String) : LoginIntent()
}

sealed class LoginState : ViewState {
    object Idle : LoginState()
    object ValidateDataSuccess : LoginState()
    data class Error(@StringRes var errorMessage: Int) : LoginState()
}

