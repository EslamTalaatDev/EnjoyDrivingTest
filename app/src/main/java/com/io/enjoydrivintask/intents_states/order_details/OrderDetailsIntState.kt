package com.io.enjoydrivintask.intents_states.order_details

import com.io.enjoydrivintask.model.OrderMangmentModel
import com.io.enjoydrivintask.utils.ViewIntent
import com.io.enjoydrivintask.utils.ViewState

sealed class OrderDetailsIntent : ViewIntent {
    object Idle : OrderDetailsIntent()

}

sealed class OrderDetailsState : ViewState {
    object Idle : OrderDetailsState()

}

