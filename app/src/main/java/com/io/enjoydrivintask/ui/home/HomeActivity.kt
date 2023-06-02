package com.io.enjoydrivintask.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.io.enjoydrivintask.BR
import com.io.enjoydrivintask.R
import com.io.enjoydrivintask.databinding.HomeLayoutBinding
import com.io.enjoydrivintask.intents_states.home.HomeIntent
import com.io.enjoydrivintask.intents_states.home.HomeState
import com.io.enjoydrivintask.ui.base.BaseActivity
import com.io.enjoydrivintask.ui.orders.OrdersActivity
import com.io.enjoydrivintask.ui.orders.OrdersAdapter
import com.io.enjoydrivintask.viewModel.home.HomeViewModel
 import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity<HomeLayoutBinding, HomeViewModel>(HomeViewModel::class.java) {
    override val layoutId: Int
        get() = R.layout.home_layout
    override val viewModelVariableId: Int
        get() = BR._all

    override fun provideViewModelFactory(): ViewModelProvider.Factory =
        createViewModelFactory { HomeViewModel() }


    var adapter: OrdersAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel.viewState.onEach {
            this.observeStates(it)
        }.launchIn(this.lifecycleScope)

        setupView()
    }

    private fun setupView() {
        binding.apply {


        }
    }

    private fun observeStates(state: HomeState) {
        when (state) {
            HomeState.Idle -> Unit
        }
        lifecycleScope.launch {
            viewModel.intents.send(HomeIntent.Idle)
        }
    }

    // Add this new method
    fun onOrderManagementClick(view: View) {
        // Handle the click event logic here
        startActivity(Intent(this@HomeActivity, OrdersActivity::class.java))
    }

}