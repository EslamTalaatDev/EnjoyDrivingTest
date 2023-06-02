package com.io.enjoydrivintask.ui.order_details

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.io.enjoydrivintask.BR
import com.io.enjoydrivintask.R
import com.io.enjoydrivintask.databinding.OrderDetailsLayoutBinding
import com.io.enjoydrivintask.databinding.OrdersLayoutBinding
import com.io.enjoydrivintask.intents_states.order_details.OrderDetailsIntent
import com.io.enjoydrivintask.intents_states.order_details.OrderDetailsState
import com.io.enjoydrivintask.intents_states.orders.OrderMangmentIntent
import com.io.enjoydrivintask.intents_states.orders.OrderMangmentState
import com.io.enjoydrivintask.model.OrderMangmentModel
import com.io.enjoydrivintask.ui.base.BaseActivity
import com.io.enjoydrivintask.utils.SendSingleItemListener
import com.io.enjoydrivintask.viewModel.order_details.OrderDetailsViewModel
import com.io.enjoydrivintask.viewModel.orders.OrderMangmentViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class OrderDetailsActivity : BaseActivity<OrderDetailsLayoutBinding, OrderDetailsViewModel>(
    OrderDetailsViewModel::class.java
) {
    override val layoutId: Int
        get() = R.layout.order_details_layout
    override val viewModelVariableId: Int
        get() = BR._all

    override fun provideViewModelFactory(): ViewModelProvider.Factory =
        createViewModelFactory { OrderDetailsViewModel() }


     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel.viewState.onEach {
            this.observeStates(it)
        }.launchIn(this.lifecycleScope)
         setupView()

    }

    private fun setupView() {
        val data = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("model", OrderMangmentModel::class.java)!!
        } else {
            intent.getParcelableExtra("model")!!
        }
         binding.data=data
    }

    private fun observeStates(state: OrderDetailsState) {
        when (state) {
            OrderDetailsState.Idle -> Unit
        }
        lifecycleScope.launch {
            viewModel.intents.send(OrderDetailsIntent.Idle)
        }
    }

    // Add this new method
    fun onOrderManagementClick(view: View) {
        // Handle the click event logic here
    }


}