package com.io.enjoydrivintask.ui.orders

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.io.enjoydrivintask.BR
import com.io.enjoydrivintask.R
import com.io.enjoydrivintask.databinding.OrdersLayoutBinding
import com.io.enjoydrivintask.intents_states.orders.OrderMangmentIntent
import com.io.enjoydrivintask.intents_states.orders.OrderMangmentState
import com.io.enjoydrivintask.model.OrderMangmentModel
import com.io.enjoydrivintask.ui.base.BaseActivity
import com.io.enjoydrivintask.utils.SendSingleItemListener
import com.io.enjoydrivintask.viewModel.orders.OrderMangmentViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class OrdersActivity : BaseActivity<OrdersLayoutBinding, OrderMangmentViewModel>(
    OrderMangmentViewModel::class.java
) {
    override val layoutId: Int
        get() = R.layout.orders_layout
    override val viewModelVariableId: Int
        get() = BR._all

    override fun provideViewModelFactory(): ViewModelProvider.Factory =
        createViewModelFactory { OrderMangmentViewModel() }


    var adapter: OrdersAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel.viewState.onEach {
            this.observeStates(it)
        }.launchIn(this.lifecycleScope)

        setupView()
    }

    private fun setupView() {
        lifecycleScope.launch {
            viewModel.intents.send(OrderMangmentIntent.FetchOrderMangment)
        }
    }

    private fun observeStates(state: OrderMangmentState) {
        when (state) {
            OrderMangmentState.Idle -> Unit
            is OrderMangmentState.OrderMangmentData -> {
                adapterImplementation(state.orderMangmentModel)
            }
        }
        lifecycleScope.launch {
            viewModel.intents.send(OrderMangmentIntent.Idle)
        }
    }

    // Add this new method
    fun onOrderManagementClick(view: View) {
        // Handle the click event logic here
    }

    fun adapterImplementation(data: List<OrderMangmentModel>) {

        adapter = OrdersAdapter(SendSingleItemListener {
            Log.e("checkClick", "${it.orderRef}")
        })
        binding.rvOrders.adapter = adapter
        adapter!!.submitList(data)
    }
}