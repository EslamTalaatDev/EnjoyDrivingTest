package com.io.enjoydrivintask.viewModel.orders

import com.io.enjoydrivintask.intents_states.orders.OrderMangmentIntent
import com.io.enjoydrivintask.intents_states.orders.OrderMangmentState
import com.io.enjoydrivintask.model.OrderMangmentModel
import com.io.enjoydrivintask.ui.base.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow

class OrderMangmentViewModel(
    intents: Channel<OrderMangmentIntent> = Channel(Channel.CONFLATED),
    state: MutableStateFlow<OrderMangmentState> = MutableStateFlow(OrderMangmentState.Idle)

) :
    BaseViewModel<OrderMangmentIntent, OrderMangmentState>(intents) {
    private val _viewState: MutableStateFlow<OrderMangmentState> = state

    val viewState: StateFlow<OrderMangmentState>
        get() = this._viewState

    override suspend fun handleIntents() {
        this.intents.consumeAsFlow().collect {
            when (it) {
                is OrderMangmentIntent.Idle -> {
                    _viewState.value =
                        this.viewStateReducer(OrderMangmentState.Idle)
                }
                is OrderMangmentIntent.FetchOrderMangment -> {
                    _viewState.value =
                        this.viewStateReducer(orderMangment())
                }

            }
        }

    }

    override fun viewStateReducer(stateData: OrderMangmentState): OrderMangmentState {
        return when (stateData) {
            OrderMangmentState.Idle -> stateData
            is OrderMangmentState.OrderMangmentData -> stateData

        }
    }

    private fun orderMangment(): OrderMangmentState =
        OrderMangmentState.OrderMangmentData(fakeOrderList())

    //
    fun fakeOrderList(): ArrayList<OrderMangmentModel> {
        return arrayListOf(
            OrderMangmentModel(
                "100231",
                "https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8fDA%3D&w=1000&q=80",
                "pending"
            ),
            OrderMangmentModel(
                "76775123",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgWy3DLSoDNZxaoOiVo3G9I7-fXtRAztlpB8YtYejl&s",
                "confirmed"
            ),
            OrderMangmentModel(
                "997967",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg",
                "canceled"
            ),
            OrderMangmentModel(
                "112311",
                "https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8fDA%3D&w=1000&q=80",
                "pending"
            ),
            OrderMangmentModel(
                "76234123",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgWy3DLSoDNZxaoOiVo3G9I7-fXtRAztlpB8YtYejl&s",
                "confirmed"
            ),
            OrderMangmentModel(
                "994567",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg",
                "canceled"
            ),
        )
    }

}