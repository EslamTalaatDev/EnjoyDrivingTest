package com.io.enjoydrivintask.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.io.enjoydrivintask.BR
import com.io.enjoydrivintask.R
import com.io.enjoydrivintask.databinding.SplashLayoutBinding
import com.io.enjoydrivintask.intents_states.splash.SplashIntent
import com.io.enjoydrivintask.intents_states.splash.SplashState
import com.io.enjoydrivintask.ui.base.BaseActivity
import com.io.enjoydrivintask.ui.login.LoginActivity
import com.io.enjoydrivintask.viewModel.splash.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SplashActivity :
    BaseActivity<SplashLayoutBinding, SplashViewModel>(SplashViewModel::class.java) {
    override val layoutId: Int
        get() = R.layout.splash_layout
    override val viewModelVariableId: Int
        get() = BR._all

    override fun provideViewModelFactory(): ViewModelProvider.Factory =
        createViewModelFactory { SplashViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel.viewState.onEach {
            this.observeStates(it)
        }.launchIn(this.lifecycleScope)

        setupView()
    }

    private fun setupView() {
        lifecycleScope.launch {
            delay(5000)
            viewModel.intents.send(SplashIntent.CompleteSplashIntent)
        }
    }

    private fun observeStates(state: SplashState) {
        when (state) {
            SplashState.Idle -> Unit
            SplashState.CompleteSplashState -> {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
        }
        lifecycleScope.launch {
            viewModel.intents.send(SplashIntent.Idle)
        }
    }

}