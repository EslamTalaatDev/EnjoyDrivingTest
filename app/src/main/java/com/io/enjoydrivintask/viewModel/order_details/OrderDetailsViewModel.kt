package com.io.enjoydrivintask.viewModel.order_details

import com.io.enjoydrivintask.intents_states.order_details.OrderDetailsIntent
import com.io.enjoydrivintask.intents_states.order_details.OrderDetailsState
import com.io.enjoydrivintask.intents_states.orders.OrderMangmentIntent
import com.io.enjoydrivintask.intents_states.orders.OrderMangmentState
import com.io.enjoydrivintask.model.OrderMangmentModel
import com.io.enjoydrivintask.ui.base.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow

class OrderDetailsViewModel(
    intents: Channel<OrderDetailsIntent> = Channel(Channel.CONFLATED),
    state: MutableStateFlow<OrderDetailsState> = MutableStateFlow(OrderDetailsState.Idle)

) :
    BaseViewModel<OrderDetailsIntent, OrderDetailsState>(intents) {
    private val _viewState: MutableStateFlow<OrderDetailsState> = state

    val viewState: StateFlow<OrderDetailsState>
        get() = this._viewState

    override suspend fun handleIntents() {
        this.intents.consumeAsFlow().collect {
            when (it) {
                is OrderDetailsIntent.Idle -> {
                    _viewState.value =
                        this.viewStateReducer(OrderDetailsState.Idle)
                }


            }
        }

    }

    override fun viewStateReducer(stateData: OrderDetailsState): OrderDetailsState {
        return when (stateData) {
            OrderDetailsState.Idle -> stateData


        }
    }


}