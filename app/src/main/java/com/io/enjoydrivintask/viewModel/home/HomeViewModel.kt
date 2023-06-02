package com.io.enjoydrivintask.viewModel.home

import com.io.enjoydrivintask.intents_states.home.HomeIntent
import com.io.enjoydrivintask.intents_states.home.HomeState
import com.io.enjoydrivintask.ui.base.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow

class HomeViewModel(
    intents: Channel<HomeIntent> = Channel(Channel.CONFLATED),
    state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Idle)

) :
    BaseViewModel<HomeIntent, HomeState>(intents) {
    private val _viewState: MutableStateFlow<HomeState> = state

    val viewState: StateFlow<HomeState>
        get() = this._viewState

    override suspend fun handleIntents() {
        this.intents.consumeAsFlow().collect {
            when (it) {
                is HomeIntent.Idle -> {
                    _viewState.value =
                        this.viewStateReducer(HomeState.Idle)
                }

            }
        }

    }

    override fun viewStateReducer(stateData: HomeState): HomeState {
        return when (stateData) {
            HomeState.Idle -> stateData

        }
    }

//   private fun homeCategories():HomeState = HomeState.HomeCategoriesData(fakeCategories())
//
//    fun fakeOptions():ArrayList<HomeCategoriesModel>{
//       return arrayListOf(
//            HomeCategoriesModel("order management", "https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8fDA%3D&w=1000&q=80"),
//            HomeCategoriesModel("store settings", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgWy3DLSoDNZxaoOiVo3G9I7-fXtRAztlpB8YtYejl&s"),
//            HomeCategoriesModel("reports", "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg"),
//        )
//    }

}