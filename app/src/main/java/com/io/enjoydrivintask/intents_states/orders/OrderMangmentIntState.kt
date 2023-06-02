package com.io.enjoydrivintask.intents_states.orders

import com.io.enjoydrivintask.model.OrderMangmentModel
import com.io.enjoydrivintask.utils.ViewIntent
import com.io.enjoydrivintask.utils.ViewState

sealed class OrderMangmentIntent : ViewIntent {
    object Idle : OrderMangmentIntent()
    object FetchOrderMangment : OrderMangmentIntent()

}

sealed class OrderMangmentState : ViewState {
    object Idle : OrderMangmentState()
    class OrderMangmentData(var orderMangmentModel: List<OrderMangmentModel>) : OrderMangmentState()

}

