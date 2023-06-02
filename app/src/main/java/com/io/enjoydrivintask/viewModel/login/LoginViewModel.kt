package com.io.enjoydrivintask.viewModel.login

import android.text.TextUtils
import com.io.enjoydrivintask.R
import com.io.enjoydrivintask.intents_states.login.LoginIntent
import com.io.enjoydrivintask.intents_states.login.LoginState
import com.io.enjoydrivintask.ui.base.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow

class LoginViewModel(
    intents: Channel<LoginIntent> = Channel(Channel.CONFLATED),
    state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Idle)

) :
    BaseViewModel<LoginIntent, LoginState>(intents) {
    private val _viewState: MutableStateFlow<LoginState> = state

    val viewState: StateFlow<LoginState>
        get() = this._viewState

    override suspend fun handleIntents() {
        this.intents.consumeAsFlow().collect {
            when (it) {
                is LoginIntent.Idle -> {
                    _viewState.value =
                        this.viewStateReducer(LoginState.Idle)
                }
                is LoginIntent.ValidateData -> {
                    _viewState.value =
                        this.viewStateReducer(validateData(it.userName, it.password))
                }


            }
        }

    }

    override fun viewStateReducer(stateData: LoginState): LoginState {
        return when (stateData) {
            LoginState.Idle,
            LoginState.ValidateDataSuccess -> {
                stateData
            }
            is LoginState.Error -> {
                stateData
            }
        }
    }

    private fun validateData(userName: String, password: String): LoginState {

        return if (TextUtils.isEmpty(userName.trim())) {
            LoginState.Error(R.string.put_name)
        } else if (userName.length<6){
            LoginState.Error(R.string.name_length_error)
        } else if (TextUtils.isEmpty(password)) {
            LoginState.Error(R.string.put_password)
        } else if (password.length<6){
            LoginState.Error(R.string.password_length_error)
        } else {
            LoginState.ValidateDataSuccess
        }

    }

}