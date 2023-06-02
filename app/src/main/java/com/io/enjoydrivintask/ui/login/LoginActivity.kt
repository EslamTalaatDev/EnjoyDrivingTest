package com.io.enjoydrivintask.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.io.enjoydrivintask.BR
import com.io.enjoydrivintask.R
import com.io.enjoydrivintask.databinding.LoginLayoutBinding
import com.io.enjoydrivintask.intents_states.login.LoginIntent
import com.io.enjoydrivintask.intents_states.login.LoginState
import com.io.enjoydrivintask.ui.base.BaseActivity
import com.io.enjoydrivintask.ui.home.HomeActivity
import com.io.enjoydrivintask.utils.hideKeyboard
import com.io.enjoydrivintask.utils.showMessage
import com.io.enjoydrivintask.viewModel.login.LoginViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginActivity :
    BaseActivity<LoginLayoutBinding, LoginViewModel>(LoginViewModel::class.java) {
    override val layoutId: Int
        get() = R.layout.login_layout
    override val viewModelVariableId: Int
        get() = BR._all

    override fun provideViewModelFactory(): ViewModelProvider.Factory =
        createViewModelFactory { LoginViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel.viewState.onEach {
            this.observeStates(it)
        }.launchIn(this.lifecycleScope)
    }


    fun btnLogin(view: View) {
        view.hideKeyboard()
        lifecycleScope.launch {
            viewModel.intents.send(
                LoginIntent.ValidateData(
                    binding.etUsername.text.toString(),
                    binding.etPassword.text.toString()
                )
            )
        }
    }

    private fun observeStates(state: LoginState) {
        when (state) {
            is LoginState.Error -> {
                showMessage(getString(state.errorMessage))
            }
            is LoginState.ValidateDataSuccess -> {
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                finish()
            }
            is LoginState.Idle -> Unit
        }
        lifecycleScope.launch {
            viewModel.intents.send(LoginIntent.Idle)
        }

    }

}