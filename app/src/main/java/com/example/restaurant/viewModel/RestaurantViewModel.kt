package com.example.restaurant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restaurant.model.Restaurant
import com.example.restaurant.model.repository.remote.RemoteRepository
import com.example.restaurant.model.states.StatesAPI
import com.example.restaurant.utils.extensions.runTaskOnBackground

internal class RestaurantViewModel(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val _successLiveData = MutableLiveData<Restaurant>()
    val successLiveData: LiveData<Restaurant> = _successLiveData

    private val _errorLiveData = MutableLiveData<StatesAPI>()
    val errorLiveData: LiveData<StatesAPI> = _errorLiveData

    @Suppress("UNCHECKED_CAST")
    fun getRestaurant(key: String) {
        runTaskOnBackground {
            when (val value = remoteRepository.getRestaurant(key)) {
                is StatesAPI.Success<*> -> {
                    val list = value.data as Restaurant
                    _successLiveData.postValue(list)
                }
                is StatesAPI.Error -> {
                    when (value.error) {
                        is StatesAPI.ClientErrorViewState -> {
                            _errorLiveData.postValue(StatesAPI.ClientErrorViewState)
                        }
                        is StatesAPI.ServerErrorViewState -> {
                            _errorLiveData.postValue(StatesAPI.ServerErrorViewState)
                        }
                        else -> {
                            _errorLiveData.postValue(StatesAPI.Exception)
                        }
                    }
                }
            }
        }
    }

    internal fun hasASuccessfulCallAlreadyBeenMade(): Boolean {
        successLiveData.value?.let {
            return true
        } ?: run {
            return false
        }
    }
}
