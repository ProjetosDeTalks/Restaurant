package com.example.restaurant.model.states

sealed class StatesAPI {
    data class Success<T>(val data: T) : StatesAPI()
    data class Error(val error: StatesAPI) : StatesAPI()
    object ClientErrorViewState : StatesAPI()
    object ServerErrorViewState : StatesAPI()
    object Exception : StatesAPI()
    data class Generic(val generic: String = "") : StatesAPI()
}
